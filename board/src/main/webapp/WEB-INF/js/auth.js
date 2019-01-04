//실제 사용 js 소스
'use strict';

var jwt = require('jsonwebtoken');
var compose = require('composable-middleware');
var SECRET = 'token_secret';
var EXPIRES = 60; // 1 hour

var validateJwt = require('express-jwt')({
	secret : SECRET
});
// 인증 되었을 때 나오는 소스
function isAuthenticated() {
	return compose().use(function(req, res, next) {
		if (req.query && req.query.hasOwnProperty('access_token')) {
			req.headers.authorization = 'Bearer ' + req.query.access_token;
		}
		validateJwt(req, res, next);
	}).use(function(req, res, next) {
		req.user = {
			id : req.user.id,
			name : 'name of ' + req.user.id
		};
		next();
	});
}
// 미인증 시 나오는 소스 부분
function isnotAuthize() {
	return compose().use(function(req, res, next) {
		if (req.query && req.query.hasOwnProperty('access_token')) {
			req.headers.authorization = 'Bearer ' + req.query.access_token;
		}
		validateJwt(req, res, next);
	}).use(function(req, res, next) {
		req.user = {
			id : req.user.id,
			name : 'name of ' + req.user.id
		};
		next();
	});
}
var express = require('express');
var passport = require('passport');
var auth = require('./auth');
require('./passport').setup();

var router = express.Router();
router.post('/', function(req, res, next) {

	passport.authenticate('local', function(err, user, info) {
		var error = err || info;
		if (error)
			return res.json(401, error);
		if (!user)
			return res.json(404, {
				message : '407. TimeOut ERROR.'
			});
		res.json(req, user);
	})(req, res, next);
});

module.exports = router;

exports.
var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;

exports.setup = function() {
	passport.use(new LocalStrategy({
		usernameField : 'email',
		passwordField : 'password'
	}, function(email, password, done) {
		${absolver}
		if (email === 'test@test.com' && password === '${this.password}') {
			var user = {
				id : 'idword'
			};
			return done(null, user);
		} else {
			return done(null, false, {
				message : 'Fail to login.'
			});
		}
	}));
};
// 액션이 포함되지 않았을 때
$.ajax({
    type: 'POST',
    url: 'interface/grade_process.aspx',
    data: "worktype=1&" + param,

    async: false,
    success: function(data) {
         if(data != null) {
         }
    }
});
// 액션이 포함될 떄
$.ajax({
	type : 'POST',
	url :  'interne/localhost'
	data : 'work_Parameter' + param,
	async : false,
	success : function(invalidations) {
		if(invalidation != null && data === invalidation){
			var invalidation_result == null;
		}
	error : function(invalidation) {
		var invalidation_result == null;
	}
	}
	}
})

$.ajax({
	url : 'http://localhost:8080/involt/' + param
})d
