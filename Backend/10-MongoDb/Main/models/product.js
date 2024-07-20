const { ObjectId } = require("mongodb");
const getDb = require("../util/database").getDb;

class Product {
  constructor(title, description, imageUrl, price, id, userId) {
    // edit is a data mutation -> Model -> params change to detect if edit/save -> DRY principle
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.price = price;
    this._id = id ? new ObjectId(id) : null;
    this.userId = userId;
  }

  save() {
    const db = getDb();
    // remains the same.
    let dbOp;
    if (this._id) {
      dbOp = db
        .collection("product")
        // $set is a mongoDb reserved document -> which recieves an obj which compares and updates the values and the reference of the obj.
        .updateOne({ _id: this._id }, { $set: this }); // won't updates -> if the types of values of the properties are different.
    } else {
      dbOp = db.collection("product").insertOne(this);
    }

    // return db
    //   .collection("product")
    //   .insertOne(this) // automatically creates a new id if not present

    return dbOp
      .then((result) => {
        console.log(result);
        return result;
      })
      .catch((err) => {
        console.log(err);
      });
  }

  static fetchAll() {
    // find returns a cursor (obj. provided by mongoDb)
    // to array returns a promise
    const db = getDb();
    return db
      .collection("product")
      .find()
      .toArray()
      .then((products) => {
        // console.log(products);
        return products;
      })
      .catch((err) => console.log(err));
  }

  static findById(id) {
    const db = getDb();
    return db
      .collection("product")
      .find({ _id: new ObjectId(id) }) // comparing
      .next()
      .then((product) => {
        // console.log(product);
        return product;
      })
      .catch((err) => console.log(err));
  }

  static deleteById(id) {
    const db = getDb();
    return db.collection("product");
  }

  static deleteById(prodId) {
    const db = getDb();
    return db
      .collection("product")
      .deleteOne({ _id: new ObjectId(prodId) })
      .then(() => console.log("Deleted"))
      .catch((err) => console.log(err));
  }
}

module.exports = Product;

// const Sequelize = require('sequelize');

// const sequelize = require('../util/database');

// const Product = sequelize.define('product', {
//   id: {
//     type: Sequelize.INTEGER,
//     autoIncrement: true,
//     allowNull: false,
//     primaryKey: true
//   },
//   title: Sequelize.STRING,
//   price: {
//     type: Sequelize.DOUBLE,
//     allowNull: false
//   },
//   imageUrl: {
//     type: Sequelize.STRING,
//     allowNull: false
//   },
//   description: {
//     type: Sequelize.STRING,
//     allowNull: false
//   }
// });

// module.exports = Product;

// mongoDb also used BSON cuz of dif data types stored in it -> like ObjectId('fsdfdf')..... ->
// objectId is an object provided by mongoDb ->
