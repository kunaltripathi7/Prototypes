const { validationResult } = require("express-validator");
const Post = require("../models/post");
const path = require("path");
const fs = require("fs");
const io = require("../socket");

exports.getPosts = (req, res, next) => {
  const currentPage = req.params.page || 1;
  const perPage = 2;
  let totalItems;
  Post.find()
    .countDocuments()
    .then((count) => {
      totalItems = count;
      return Post.find()
        .skip(perPage * (currentPage - 1))
        .limit(perPage);
    })
    .then((posts) => {
      res.status(200).json({
        message: "posts are successfully fetched",
        posts: posts,
        totalItems: totalItems,
      });
    })
    .catch((err) => {
      if (!err.statusCode) {
        err.statusCode = 422;
        next(err);
      }
    });
};

exports.getPost = (req, res, next) => {
  const postId = req.params.postId;
  console.log(postId);
  Post.findById(postId)
    .then((post) => {
      if (!post) {
        const error = new Error("Post can't be found");
        error.statusCode = 422;
        throw error;
      }
      res.status(200).json({
        message: "post fetched",
        post: post,
      });
    })
    .catch((err) => {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    });
};

//status codes
exports.createPost = (req, res, next) => {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    const error = new Error("Validation Failed, entered Data is incorrect");
    error.statusCode = 422;
    throw err;
  }

  if (!req.file) {
    const error = new Error("No image Provided");
    error.statusCode = 422;
    throw err;
  }

  const title = req.body.title;
  const content = req.body.content;
  const imageUrl = req.file.path;

  const post = new Post({
    title: title,
    imageUrl: imageUrl,
    content: content,
    creator: req.userId,
  });
  let creator;

  post
    .save()
    .then((result) => {
      return user.findById(req.userId);
    })
    .then((user) => {
      creator = user;
      user.posts.push(post);
      return user.save();
    })
    .then((result) => {
      io.getIO().emit("posts", { action: "create", post: post });
      res.status(201).json({
        message: "Post Created Successfully",
        post: post,
        creator: { _id: creator._id, name: creator.name },
      });
    })
    .catch((err) => {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    });
};

exports.updatePost = (req, res, next) => {
  const postId = req.params.postId;
  const title = req.body.title;
  const content = req.body.content;
  let imageUrl = req.body.image;
  if (req.file) {
    imageUrl = req.file.path;
  }
  if (!imageUrl) {
    const error = new Error("File could not be found");
    error.statusCode = 422;
    throw err;
  }

  Post.findById(postId)
    .then((post) => {
      if (!post) {
        const err = new Error("Post could not be found");
        err.statusCode = 404;
        throw err;
      }

      if (post.creator.toString() !== req.userId) {
        const error = new Error("Unauthorized");
        error.statusCode = 403;
        throw error;
      }

      if (imageUrl !== post.imageUrl) {
        clearImage(post.imageUrl);
      }
      post.title = title;
      post.content = content;
      post.imageUrl = imageUrl;
      return post.save();
    })
    .then((result) =>
      res.status(200).json({
        message: "Post updated",
        post: result,
      })
    )
    .catch((err) => {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    });
};

const clearImage = (filePath) => {
  filePath = path.join(__dirname, "..", filePath);
  fs.unlink(filePath, (err) => console.log(err));
};

exports.deletePost = (req, res, next) => {
  const postId = req.params.postId;
  Post.findById(postId)
    .then((post) => {
      if (!post) {
        const err = new Error("Couldn't find a post");
        err.statusCode = 422;
        throw err;
      }

      if (post.creator.toString() !== req.userId) {
        const error = new Error("Unauthorized");
        error.statusCode = 403;
        throw error;
      }
      //check loggedIN user
      clearImage(post.imageUrl);
      return Post.findByIdAndDelete(postId);
    })
    .then((result) => {
      return User.findById(req.userId);
    })
    .then((user) => {
      user.posts.pull(postId); // how to delete a postId from the user.
      res.status(200).json({
        message: "deletedPost",
      });
    })
    .catch((err) => {
      if (!err.statusCode) {
        err.statusCode = 500;
      }
      next(err);
    });
};
