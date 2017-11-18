angular.module('AppMain')
.factory('pedido', function($rootScope, $localStorage) {
  var userId = '';
  if ('user' in $rootScope) {
    userId = $rootScope.user.id ? $rootScope.user.id : '';
  }

  if ('shoppingList' in $localStorage) {
    $rootScope.shoppingList = $localStorage.shoppingList;
  } else {
    $rootScope.shoppingList = {
      products: [],
      id: userId + '-' + Math.random()
    };
  }

  $rootScope.$watch('shoppingList', function() {
    console.log($rootScope.shoppingList);
  });

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
        $rootScope.shoppingList.products.push(product);
      } else {
        alert("Não foi possível adicionar esse produto, houve um erro!");
        console.log("O produto adicionado não tinha ID");
      }
      $localStorage.$default({shoppingList: $rootScope.shoppingList});
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
      $localStorage.$default({shoppingList: $rootScope.shoppingList});
    },

    delete: function(index) {
      if (typeof $rootScope.shoppingList.products[index] != 'undefined') {
        if (!delete $rootScope.shoppingList.products[index]) {
          console.log("Um erro inesperado ao deletar esse produto!");
          alert("Um erro inesperado ao deletar esse produto!");
        }
      }
      $localStorage.$default({shoppingList: $rootScope.shoppingList});
    }
  };
});
