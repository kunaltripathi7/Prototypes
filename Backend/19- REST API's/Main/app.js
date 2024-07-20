const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
// const fs = require('fs');

const path = require("path");
const app = express();
const feedRoutes = require("./routes/feed");
const authRoutes = require("./routes/auth");
const multer = require("multer");

const fileStorage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, "images");
  },
  filename: (req, file, cb) => {
    cb(null, Math.random().toFixed(5) + "-" + file.originalname);
  },
});

const filefilter = (req, file, cb) => {
  if (
    file.mimetype === "image/png" ||
    file.mimetype === "image/jpeg" ||
    file.mimetype === "image/jpg"
  ) {
    cb(null, true);
  } else {
    cb(null, false);
  }
};
// earlier we used to parse in www/url-encoded/ which was given on forms
app.use(bodyParser.json()); // to parse the incoming json data which we will recieve to get it from the req.body.
// any request starting with feed will make into this route.
app.use(
  multer({ storage: fileStorage, filefilter: filefilter }).single("image")
);

// fs.readFileSync('server.key');
// fs.readFileSync('server.cert');

// statically serve the images from the server
app.use("/images", express.static(path.join(__dirname, "images")));

app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Methods",
    "GET, POST, DELETE, PUT, PATCH"
  );
  res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
  next();
});

app.use("/feed", feedRoutes);
app.use("/auth", authRoutes);

app.use((error, req, res, next) => {
  console.log(error);
  const status = error.status || 500;
  const message = error.message;
  res.status(status).json({
    message: message,
  });
});

mongoose
  .connect(
    "mongodb+srv://kunaltripathi2153:MMA6FYC0Lp1tSDVQ@cluster0.hklv1vl.mongodb.net/messages?retryWrites=true&w=majority&appName=Cluster0"
  )
  .then((result) => {
    const server = app.listen(8080);
    const io = require("./socket").init(server);
    io.on("connection", (socket) => {
      // on every new client connection this func will execute.
      console.log("client connected");
    });
  })
  .catch((err) => console.log(err));
