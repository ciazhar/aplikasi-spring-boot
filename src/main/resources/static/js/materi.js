var materiapp = angular.module ('materiapp',[]);
materiapp.controller('MateriController', function($scope){
    $scope.daftarMateri = [
      'Java Fundamental',
      'Spring Framework'
    ];

    $scope.tambahMateri = function(){
      $scope.daftarMateri.push($scope.namaMateri);
    }

    $scope.hapusMateri = function(materi){
      var lokasiIndex = $scope.daftarMateri.indexOf(materi);
      if (lokasiIndex>-1) {
        $scope.daftarMateri.splice(lokasiIndex,1);
      }

    }
});

materiapp.controller('GenerateMateriController', function($http, $scope){
  $scope.daftarMateri = {};

  $scope.listMateri = function(){
    $http.get('/api/materi').then(sukses,gagal);

    function sukses(response){
      $scope.daftarMateri = response.data;
      console.log($scope.daftarMateri);
    };

    function gagal(response){
      console.log(response);
      alert('Error'+response);
    };
  };
  $scope.listMateri();
});
