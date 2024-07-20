exports.getLogin = (req, res, next) => {
  //const isLoggedIn = req.get("Cookie").split("; ");
  // const isLoggedIn = req.get("Cookie").split(";")[2].trim().split("=")[1];
  console.log(req.session.isLoggedIn);
  res.render("auth/login", {
    path: "/login",
    pageTitle: "Login",
    isAuthenticated: req.session.isLoggedIn,
  });
};

exports.postLogin = (req, res, next) => {
  // req.isLoggedIn = true; // this info is not saved, every time a new request -> this data is lost cuz added at the end after travelling to app.js
  // res.setHeader("Set-Cookie", "loggedIn=true");
  // res.setHeader("Set-Cookie", "loggedIn=true; Secure Domain"); can track users by storing cookie on client side so each time you send request => google can track which sites are you visiting;

  req.session.isLoggedIn = true; // session is saved in memory right now not in database.
  res.redirect("/");
};

// the cookie is sent on every req and cookie stores the hashed id of where the sesssion is stored to validate.

exports.postLogout = (req, res, next) => {
  req.session.destroy((err) => {
    // func. runs after deleting the session
    console.log(err);
    res.redirect("/");
  });
};
