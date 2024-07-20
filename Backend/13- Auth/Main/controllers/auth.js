const User = require("../models/user");
const bcrypt = require("bcryptjs");

exports.getLogin = (req, res, next) => {
  let message = req.flash("error");
  if (message.length > 0) message = message[0];
  else message = null;
  res.render("auth/login", {
    path: "/login",
    pageTitle: "Login",
    errorMessage: message,
  });
};

exports.getSignup = (req, res, next) => {
  let message = req.flash("error");
  if (message.length > 0) message = message[0];
  else message = null;
  res.render("auth/signup", {
    path: "/signup",
    pageTitle: "Signup",
    errorMessage: message,
  });
};

exports.postLogin = (req, res, next) => {
  const email = req.body.email;
  const password = req.body.password;
  User.findOne({ email: email }).then((user) => {
    if (!user) {
      req.flash("error", "Invalid email or password");
      return res.redirect("/login");
    }
    bcrypt.compare(password, user.password).then((domatch) => {
      if (domatch) {
        req.session.isLoggedIn = true;
        req.session.user = user;
        return req.session.save((err) => {
          console.log(err);
          res.redirect("/");
        });
      }
      req.flash("error", "Invalid");
      res.redirect("/login");
    });
  });
};

exports.postSignup = (req, res, next) => {
  const email = req.body.email; // the name is same as name attri assigned
  const password = req.body.password;
  const confirmPassword = req.body.confirmPassword;
  User.findOne({ email: email }) // other meth to check is indexing the email in mongoDB
    .then((userDoc) => {
      if (userDoc) {
        req.flash("error", "Invalid email or password");
        return res.redirect("/signup");
      }
      return bcrypt.hash(password, 12).then((hashedPass) => {
        // chaining cuz hashedpass was undefined on redirecting
        const user = new User({
          email: email,
          password: hashedPass,
          cart: { items: [] },
        });
        return user.save();
      }); //salt val
    })
    .then((result) => {
      res.redirect("/login");
    })
    .catch((err) => {
      console.log(err);
    });
};

exports.postLogout = (req, res, next) => {
  req.session.destroy((err) => {
    console.log(err);
    res.redirect("/");
  });
};

// Problem: when redirecting to login on wrong pass. no way to provide err on user feedback.
// Way to pass data b/w requests -> session
