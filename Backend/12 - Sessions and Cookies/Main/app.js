const path = require("path");

const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const session = require("express-session");
const MongoDBStore = require("connect-mongodb-session")(session); // will automatically store sessions in the db pkg, returns a constructor func.

const errorController = require("./controllers/error");
const User = require("./models/user");

const MONGODB_URI =
  "mongodb+srv://kunaltripathi2153:MMA6FYC0Lp1tSDVQ@cluster0.hklv1vl.mongodb.net/shop?retryWrites=true&w=majority&appName=Cluster0";

const app = express();
//store setup
const store = new MongoDBStore({
  uri: MONGODB_URI,
  collection: "sessions",
});

app.set("view engine", "ejs");
app.set("views", "views");

const adminRoutes = require("./routes/admin");
const shopRoutes = require("./routes/shop");
const authRoutes = require("./routes/auth");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, "public")));
app.use(
  session({
    secret: "my secret",
    resave: false,
    saveUninitialized: false,
    store: store,
  }) // resave -> means that the session will not be saved on every request that is sent || saveunitialized -> no saving if nothing changes
);

app.use((req, res, next) => {
  User.findById("66658b2d3780ad5e70de9b56")
    .then((user) => {
      // user is a mongoose model with all the func/meths available
      req.user = user;
      next();
    })
    .catch((err) => console.log(err));
});

app.use("/admin", adminRoutes);
app.use(shopRoutes);
app.use(authRoutes);

app.use(errorController.get404);

// mongoose will hold this connection internally and use on each query.
mongoose
  .connect(MONGODB_URI)
  .then((result) => {
    User.findOne().then((user) => {
      // returns the first user which is found
      if (!user) {
        const user = new User({
          name: "test",
          email: "test@test.com",
          cart: {
            items: [],
          },
        });
        user.save(); // returns a promise
      }
    });
    app.listen(3000);
  })
  .catch((err) => {
    console.log(err);
  });
