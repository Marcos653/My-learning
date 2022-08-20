var _pix = require("faz-um-pix");

var code = _pix.Pix(
  "65465462123",
  "teste",
  "RIO DE JANEIRO",
  "0.10",
  "Pedido #123456",
  true
);
// return "00020126490014br.gov.bcb.pix01091234567890214Pedid…1SILVA SILVA6014RIO DE JANEIRO62070503***6304E92D"

const routes = (app) => {
  app.route("/payment-by-pix").get((req, res) => {
    console.log(code);
    res.status(200).send(code);
  });
};

module.exports = routes;
