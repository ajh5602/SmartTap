/**
 * passport.js
 */

'use strict';

var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;

exports.setup = function() {
	passport.use(new LocalStrategy({
		usernameField : 'email',
		passwordField : 'password'
	}, function(email, password, done) {

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