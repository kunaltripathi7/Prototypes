const mongodb = require("mongodb");
const MongoClient = mongodb.MongoClient;

let _db;

const mongoConnect = (callback) => {
  MongoClient.connect(
    // ongodb.net/shop?retryW -> connecting to the shop database -> if that db doesn't exist mongo will create it.
    "mongodb+srv://kunaltripathi2153:MMA6FYC0Lp1tSDVQ@cluster0.hklv1vl.mongodb.net/shop?retryWrites=true&w=majority&appName=Cluster0"
  )
    .then((client) => {
      console.log("connected");
      _db = client.db(); // storing connection to the database.
      callback();

      // callback(client); // connecting to mongoDb and not disconnecting afterwards.
    })
    .catch((err) => {
      console.log(err);
      throw err;
    });
};

const getDb = () => {
  if (_db) return _db;
  throw "No Database Connected";
};

// mongo will give connection poooling for multiple simulataneous trans.
exports.mongoConnect = mongoConnect;
exports.getDb = getDb;
