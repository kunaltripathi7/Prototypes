const { ObjectId } = require("mongodb");
const getDb = require("../util/database").getDb;

class User {
  constructor(email, name, cart, id) {
    // why accepting args from the outside???? -> cuz on new requests we want the same id & cart for reference to do operations.
    this.email = email;
    this.name = name;
    this.cart = cart;
    this._id = id;
  }

  save() {
    const db = getDb();
    return db.collection("users").insertOne(this);
  }

  addToCart(product) {
    const cartProductIndex = this.cart.items.findIndex(
      (cp) => cp.productId.toString() === product._id.toString()
    );
    const updatedCartItems = [...this.cart.items];
    let newQuantity = 1;
    if (cartProductIndex >= 0) {
      newQuantity = updatedCartItems[cartProductIndex].quantity + 1;
      updatedCartItems[cartProductIndex].quantity = newQuantity;
    } else {
      updatedCartItems.push({
        productId: new ObjectId(product._id),
        quantity: newQuantity,
      });
    }

    const updatedCart = {
      items: updatedCartItems, //
    };

    const db = getDb();
    return db
      .collection("users")
      .updateOne(
        { _id: new ObjectId(this._id) },
        { $set: { cart: updatedCart } }
      );
  }

  // added functionality of deleting the cart product if the original product has been already deleted.

  getCart() {
    const productIds = this.cart.items.map((p) => p.productId);
    const db = getDb();
    let productsF;
    return db
      .collection("product")
      .find({ _id: { $in: productIds } }) // in method -> for searching in a pool of ids
      .toArray()
      .then((products) => {
        productsF = products;
        const updatedIds = productIds.filter((id) => {
          if (products.find((p) => p._id.toString() === id.toString()))
            return true;
          return false;
        });
        this.cart = {
          items: this.cart.items.filter((i) => {
            if (
              updatedIds.find((id) => i.productId.toString() === id.toString())
            )
              return true;
            return false;
          }),
        };

        return db
          .collection("users")
          .updateOne(
            { _id: new ObjectId(this._id) },
            { $set: { cart: this.cart } }
          );
      })
      .then(() => {
        return productsF.map((product) => {
          return {
            ...product,
            quantity: this.cart.items.find((i) => {
              return i.productId.toString() === product._id.toString();
            }).quantity,
          };
        });
      });
  }

  addOrder() {
    const db = getDb();
    return this.getCart()
      .then((products) => {
        const order = {
          items: products,
          user: {
            _id: new ObjectId(this._id),
            name: this.name,
            email: this.email,
          },
        };
        return db.collection("orders").insertOne(order);
      })
      .then((res) => {
        this.cart = { items: [] };
        return db
          .collection("users")
          .updateOne(
            { _id: new ObjectId(this._id) },
            { $set: { cart: { items: [] } } }
          );
      });
  }

  getOrders() {
    const db = getDb();
    return db
      .collection("orders")
      .find({ "user._id": new ObjectId(this._id) })
      .toArray();
  }

  deleteById(prodId) {
    const updatedCartItems = this.cart.items.filter(
      (p) => p.productId.toString() !== prodId
    );
    this.cart = { items: updatedCartItems };
    const db = getDb();
    return db
      .collection("users")
      .updateOne(
        { _id: new ObjectId(this._id) },
        { $set: { cart: this.cart } }
      );
  }

  static findById(userId) {
    const db = getDb();
    return db
      .collection("users")
      .find({ _id: new ObjectId(userId) })
      .next();
  }
}

module.exports = User;
