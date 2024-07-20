const express = require("express");
const { check, body } = require("express-validator/check"); // can include specific parts of request, req contains params, cookies, body etc.
const User = require("../models/user");

const authController = require("../controllers/auth");

const router = express.Router();

router.get("/login", authController.getLogin);

router.get("/signup", authController.getSignup);

router.post(
  "/login",
  [
    body("email")
      .isEmail()
      .withMessage("Please enter a valid email address.")
      .normalizeEmail(),
    body("password", "Password has to be valid.")
      .isLength({ min: 5 })
      .isAlphanumeric()
      .trim(),
  ],
  authController.postLogin
);

// with message refers to the validator that's just before it.
router.post(
  "/signup",
  [
    check("email")
      .isEmail()
      .withMessage("Please Enter a valid Email")
      .normalizeEmail()
      .custom((value, { req }) => {
        // custom validators -> can chain also.
        // if (value === "test@test.com") throw new Error("Not a valid mail");
        // return true;
        // finding the same user in db comes under validation.
        return User.findOne({ email: value }).then((userDoc) => {
          if (userDoc) {
            return Promise.reject(
              // async validation
              "E-Mail exists already, please pick a different one"
            );
          }
        });
      }),
    body(
      "password",
      "Please Enter a pass containing only nums & text and is atleast 5 chars long" // for making it a default messsage for all checks
    )
      .isLength({ min: 5 })
      .isAlphanumeric()
      .trim(),
    body("confirmPassword")
      .trim()
      .custom((value, { req }) => {
        if (value !== req.body.password) {
          throw new Error();
        }
        return true;
      }),
  ],
  authController.postSignup
);

router.post("/logout", authController.postLogout);

router.get("/reset", authController.getReset);

router.post("/reset", authController.postReset);

router.get("/reset/:token", authController.getNewPassword);

router.post("/new-password", authController.postNewPassword);

module.exports = router;
