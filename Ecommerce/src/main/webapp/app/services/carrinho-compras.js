angular.module('AppMain')
.factory('pedido', function($rootScope, utils) {
  var userId = utils.getUser() ? utils.getUser().id : '';

  $rootScope.shoppingList = {
    products: [],
    id: userId + '-' + Math.random()
  };

  return {
    list: function() {
      var products = [];
      if (Array.isArray($rootScope.shoppingList.products)) {
         products = $rootScope.shoppingList.products;
      }
      return products;
    },

    add: function(product) {
      if ('id' in product) {
        product.quantidade = 1;
        $rootScope.shoppingList.product.push(product);
      } else {
        alert("Não foi possível adicionar esse produto, houve um erro!");
        console.log("O produto adicionado não tinha ID");
      }
    },

    update: function(index, newProductValues) {
      if (typeof $rootScope.shoppingList.products[index] != 'undefined') {
        var product = $rootScope.shoppingList.products[index];
        var properties = Object.keys(newProductValues);
        for (var property in properties) {
          if (product[property] != newProductValues[property]) {
            product[property] = newProductValues[property];
          }
        }
        $rootScope.shoppingList.product[index] = product;
      } else {
        alert("Não foi possível atualizar este produto");
      }
    },

    delete: function(index) {
      if (typeof $rootScope.shoppingList.products[index] != 'undefined') {
        if (!delete $rootScope.shoppingList.products[index]) {
          console.log("Um erro inesperado ao deletar esse produto!");
          alert("Um erro inesperado ao deletar esse produto!");
        }
      }
    }
  };
});
