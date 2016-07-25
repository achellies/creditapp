import baseApiService from './services/api/base';
import commonApiService from './services/api/common';
import loadApiService from './services/api/loan';
import userApiService from './services/api/user';
import businessComponentsService from './services/businesscomponents';
import Factories from './factories/factories';
import Filters from './filters/filters';
import commonDirectives from './directives/common';
import personalDirectives from './directives/personal';
var app = angular.module('wanda.app', ['ngAnimate', 'ngTouch', 'ngRoute', 'wanda.app.services', 'wanda.app.directive', 'wanda.app.filter']),
    appServices = angular.module('wanda.app.services', []),
    appDirectives = angular.module('wanda.app.directive', []),
    appFilters = angular.module('wanda.app.filter', []);
//==========Service========   
var services = [];
var directives = [];

services = services.concat(baseApiService, commonApiService, loadApiService, userApiService, businessComponentsService);
directives = directives.concat(commonDirectives, personalDirectives);

// regist Service-services
services.forEach(function(service) {
    var deps = service.dependences,
        name = service.name;
    deps.push(service.fn);
    appServices.service(name, deps);
});

// regist Service-factory
Factories.forEach(function(factory) {
    var deps = factory.dependences,
        name = factory.name;
    deps.push(factory.fn);
    appServices.factory(name, deps);
});
// regist Service-provider

//==========Service========

//==========Directive========

// regist Directive
directives.forEach(function(directive) {
    var deps = directive.dependences,
        name = directive.name;
    deps.push(directive.fn);
    appDirectives.directive(name, deps);
});

//==========Directive========

//==========Filters========

// regist Filters
Filters.forEach(function(filter) {
    var deps = filter.dependences,
        name = filter.name;
    deps.push(filter.fn);
    appFilters.filter(name, deps);
});

//==========Filters========

export default app;
