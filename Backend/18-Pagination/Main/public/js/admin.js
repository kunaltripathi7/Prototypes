const deleteProduct = (btn) => {
  const prodId = btn.parentNode.querySelector("[name=productId]").value;
  const csrfToken = btn.parentNode.querySelector("[name=_csrf]").value;
  //csurf package looks for the csurf token not only in the req body but also query parameters too.(headers)
  const productElement = btn.closest("article");

  fetch("/admin/product/" + prodId, {
    method: "DELETE",
    headers: {
      "csrf-token": csrfToken,
    },
  })
    .then((result) => {
      productElement.remove(productElement);
      return res.json();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
      console.log(err);
    });
};
