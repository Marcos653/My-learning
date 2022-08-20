const express = require("express");
const routes = require("./index.js")

const app = express();

app.use(express.json());
routes(app);

module.exports = app;