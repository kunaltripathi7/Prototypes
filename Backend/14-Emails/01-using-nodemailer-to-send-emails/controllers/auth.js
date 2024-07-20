const bcrypt = require("bcryptjs");
const User = require("../models/user");

// mailgun
let API_KEY = "623e9a0b0e595dcd6b844e279ffec470-8a084751-a39bfe2b";
let DOMAIN = "sandboxd2294dc67d4c44ad953b10bf81496a0b.mailgun.org";
const mailgun = require("mailgun-js")({ apiKey: API_KEY, domain: DOMAIN });

const sendMail = function (
  sender_email,
  receiver_email,
  email_subject,
  email_body
) {
  const data = {
    from: sender_email,
    to: receiver_email,
    subject: email_subject,
    text: email_body,
  };

  mailgun.messages().send(data, (error, body) => {
    if (error) console.log(error);
    else console.log(body);
  });
};

// mailgun end

exports.getLogin = (req, res, next) => {
  let message = req.flash("error");
  if (message.length > 0) {
    message = message[0];
  } else {
    message = null;
  }
  res.render("auth/login", {
    path: "/login",
    pageTitle: "Login",
    errorMessage: message,
  });
};

exports.getSignup = (req, res, next) => {
  let message = req.flash("error");
  if (message.length > 0) {
    message = message[0];
  } else {
    message = null;
  }
  res.render("auth/signup", {
    path: "/signup",
    pageTitle: "Signup",
    errorMessage: message,
  });
};

exports.postLogin = (req, res, next) => {
  const email = req.body.email;
  const password = req.body.password;
  User.findOne({ email: email })
    .then((user) => {
      if (!user) {
        req.flash("error", "Invalid email or password.");
        return res.redirect("/login");
      }
      bcrypt
        .compare(password, user.password)
        .then((doMatch) => {
          if (doMatch) {
            req.session.isLoggedIn = true;
            req.session.user = user;
            return req.session.save((err) => {
              console.log(err);
              res.redirect("/");
            });
          }
          req.flash("error", "Invalid email or password.");
          res.redirect("/login");
        })
        .catch((err) => {
          console.log(err);
          res.redirect("/login");
        });
    })
    .catch((err) => console.log(err));
};

exports.postSignup = (req, res, next) => {
  const email = req.body.email;
  const password = req.body.password;
  const confirmPassword = req.body.confirmPassword;
  User.findOne({ email: email })
    .then((userDoc) => {
      if (userDoc) {
        req.flash(
          "error",
          "E-Mail exists already, please pick a different one."
        );
        return res.redirect("/signup");
      }
      return bcrypt
        .hash(password, 12)
        .then((hashedPassword) => {
          const user = new User({
            email: email,
            password: hashedPassword,
            cart: { items: [] },
          });
          return user.save();
        })
        .then((result) => {
          res.redirect("/login");
          let sender_email =
            "brad@sandboxd2294dc67d4c44ad953b10bf81496a0b.mailgun.org";
          let receiver_email = email;
          let email_subject = "Mailgun Demo";
          let email_body = "Greetings from use";
          return sendMail(
            sender_email,
            receiver_email,
            email_subject,
            email_body
          );
        })
        .catch((err) => {
          console.log(err);
        });
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
