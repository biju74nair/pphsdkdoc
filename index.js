#!/usr/bin/env node
var express = require('express');
var path = require('path');
var favicon = require('static-favicon');

var app = express();

// view engine setup
app.use(favicon());
app.use(express.static(path.join(__dirname, 'public')));

var accesstoken = require('./services/accesstoken');
app.get('/services/accesstoken', accesstoken.accesstoken);


/// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

var server = app.listen(4000, function() {
    console.log('Express server listening on port ' + server.address().port);
});
