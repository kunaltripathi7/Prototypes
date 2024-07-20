const path = require("path");

const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const session = require("express-session");
const MongoDBStore = require("connect-mongodb-session")(session);
const csrf = require("csurf");
const flash = require("connect-flash");
const multer = require("multer");

const errorController = require("./controllers/error");
const User = require("./models/user");

const MONGODB_URI =
  "mongodb+srv://kunaltripathi2153:MMA6FYC0Lp1tSDVQ@cluster0.hklv1vl.mongodb.net/shop?retryWrites=true&w=majority&appName=Cluster0";

const app = express();
const store = new MongoDBStore({
  uri: MONGODB_URI,
  collection: "sessions",
});

const csrfProtection = csrf();

app.set("view engine", "ejs");
app.set("views", "views");

const adminRoutes = require("./routes/admin");
const shopRoutes = require("./routes/shop");
const authRoutes = require("./routes/auth");
const { error } = require("console");

const fileStorage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, "images");
  },
  filename: (req, file, cb) => {
    // Replace colons and periods with underscores, and remove any other potentially problematic characters
    const safeTimestamp = new Date()
      .toISOString()
      .replace(/[:.]/g, "_")
      .replace(/[^a-zA-Z0-9_-]/g, "");
    cb(null, safeTimestamp + "-" + file.originalname);
  },
});

const fileFilter = (req, file, cb) => {
  if (
    file.mimetype === "image/png" ||
    file.mimetype === "image/jpg" ||
    file.mimetype === "image/jpeg"
  ) {
    cb(null, true);
  } else {
    cb(null, false);
  }
};
// dest -> loc to store the data
// app.use(multer({ dest: "images" }).single("image"));
// storage -> more options

// this middleware allows your Express application to automatically parse the body of POST requests (like form submissions) and make the data easily accessible in your route handlers via req.body o/w req.body would be undefined. can't convert file types to text. pkg -> multer
app.use(bodyParser.urlencoded({ extended: false }));
app.use(
  multer({ storage: fileStorage, fileFilter: fileFilter }).single("image")
);
app.use(express.static(path.join(__dirname, "public")));
//serve the files as they were in the root folder so without the /images ||||| if (/images) in route then serve these files statically
app.use("/images", express.static(path.join(__dirname, "images")));
app.use(
  session({
    secret: "my secret",
    resave: false,
    saveUninitialized: false,
    store: store,
  })
);
app.use(csrfProtection);
app.use(flash());

// if you throw error in async code then the req won't go to the express error handler, whereas in synchronous code it will. async -> next(error) || sync -> throw new Error();
app.use((req, res, next) => {
  // throw new Error("Dummy");
  if (!req.session.user) {
    return next();
  }
  User.findById(req.session.user._id)
    // throw new Error("Dummy2");
    .then((user) => {
      if (!user) {
        return next();
      }
      req.user = user;
      next();
    })
    .catch((err) => {
      throw new Error(err); // new instance of Error obj with (err) as string
      // console.log(err);
    });
});

app.use((req, res, next) => {
  // storing in locals to send to views
  res.locals.isAuthenticated = req.session.isLoggedIn;
  res.locals.csrfToken = req.csrfToken();
  next();
});

app.use("/admin", adminRoutes);
app.use(shopRoutes);
app.use(authRoutes);

app.get("/500", errorController.get500);
app.use(errorController.get404); // catch all middleware
app.use((error, req, res, next) => {
  // error handling middleware -> special middleware
  res.redirect("/500"); // redirect sends a new request to the server (here node)
});

mongoose
  .connect(MONGODB_URI)
  .then((result) => {
    app.listen(3000);
  })
  .catch((err) => {
    console.log(err);
  });

//When specifying paths in Node.js, adding a leading forward slash (/) indeed creates an absolute path.
