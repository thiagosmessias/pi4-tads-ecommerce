/**
 * Instantialize module Requisition
 * @param {string} Module name
 */
angular.module('AppMain')
  /**
   * The service to centralize and simplify the API calls.
   */
.factory('api', function($http, host, $rootScope, $location, storage) {
  var apiAddress = location.href.substr(0, location.href.indexOf('/', location.origin.length)) + '/';

  return {
    /**
     * Make the post call to api
     * @param  {string} route   Insert url, after domain, to submit data via REST and call to specific controller
     * @param  {object} data    All datas to submit.
     * @param  {string} token   Is the api key for firebase validation.
     * @return {promise}        return the promise method to wait for api response
     */
    post: function(route, data, token) {
      // Check if the data is set
      if (typeof data !== 'object') {
        data = {};
      }
      return $http.post(apiAddress + route, data, {
        headers: {
          auth: token,
          env: 'admin'
        }
      });
    },

    /**
     * Make the get call to api
     * @param  {string} route  Insert url, after domain, to submit data via REST and call to specific controller
     * @param  {object} data    All datas to submit.
     * @param  {string} token   Is the api key for firebase validation.
     * @return {promise}      The promisse to applicant work with specifc cases
     */
    get: function(route, data, token) {
      // Check if the data is set
      if (typeof data !== 'object') {
        data = {};
      }
      return $http.get(apiAddress + route, {
        headers: {
          auth: token,
          env: 'admin'
        },
        params: data
      });
    },

    /**
    * Make the get call to api
    * @param  {string} route  Insert url, after domain, to submit data via REST and call to specific controller
    * @param  {object} data    All datas to submit.
    * @param  {string} token   Is the api key for firebase validation.
    * @return {promise}      The promisse to applicant work with specifc cases
    */
    put: function(route, data, token) {
      // Check if the data is set
      if (typeof data !== 'object') {
        data = {};
      }
      return $http.put(apiAddress + route, data, {
        headers: {
          auth: token,
          env: 'admin'
        }
      });
    },

    /**
    * Make the get call to api
    * @param  {string} route  Insert url, after domain, to submit data via REST and call to specific controller
    * @param  {object} data    All datas to submit.
    * @param  {string} token   Is the api key for firebase validation.
    * @return {promise}      The promisse to applicant work with specifc cases
    */
    delete: function(route, data, token) {
      // Check if the data is set
      if (typeof data !== 'object') {
        data = {};
      }
      return $http.put(apiAddress + route, data, {
        headers: {
          auth: token,
          env: 'admin'
        }
      });
    },

    /**
     * Simplify the decision to use functions post or get to api
     * @param  {string} route    Insert url, after domain, to submit data via REST and call to specific controller
     * @param  {string} method    With value 'post' to use post method or empty/undefined or 'get' to use get method
     * @param  {object} data      All datas to submit.
     * @return {function}        Return the function specific to method selected
     */
    call: function(route, method, data) {
      if (typeof $rootScope.firebase !== 'undefined') {
        return this.makeCall(route, method, data);
      }
    },

    makeCall: function(route, method, data) {
      var token = '';
      switch (method) {
        case 'post':
          tmp = this.post;
          break;
        case 'get':
          tmp = this.get;
          break;
        case 'put':
          tmp = this.put;
          break;
        case 'delete':
          tmp = this.delete;
          break;
        default:
          console.error("Unrecognized method %s", method);
      }

      tmp(route, data, token).then(function(data) {
        defer.resolve(data);
      }, function(err) {
        if (err.status === 401) {
          $rootScope.logout();
        } else if (err.status === 500) {
          alert("Unexpected error in server");
          $rootScope.logout();
        }
        defer.reject(err);
      });
    }
  };
});
