  function dataShareService() {
      var savedData = {}

      function save(key, data) {
          savedData[key] = data;
          return data;
      }

      function get(key) {
          return savedData[key];
      }

      function clear(key) {
          if (savedData.hasOwnProperty(key)) { delete savedData[key]; }
          return savedData[key];
      }

      return {
          save: save,
          get: get,
          clear: clear
      }
  }

  function dataShareServiceGlobal() {
      var savedData = localStorage;

      function save(key, data) {
          savedData[key] = JSON.stringify(data);
          return data;
      };

      function get(key) {
          var data = savedData[key];
          return data;
      };

      function clear(key) {
          if (savedData.hasOwnProperty(key)) { savedData.removeItem(key); }
      };
      return {
          save: save,
          get: get,
          clear: clear
      }
  }
  /**
   * export wd-services module
   */
  export default [{
      name: 'dataShareService',
      dependences: [],
      fn: dataShareService
  }, {
      name: 'dataShareServiceGlobal',
      dependences: [],
      fn: dataShareServiceGlobal
  }];
