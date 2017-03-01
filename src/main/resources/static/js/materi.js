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

  $scope.simpanMateri = function(){
    $http.post('/api/materi', $scope.materi).then(sukses,gagal);
    function sukses(response){
       $scope.listMateri();
    }
    function gagal(response){
       console.log(response);
       alert('Error : '+response);
     };
  };

  $scope.hapusMateri = function(materi){
    $http.delete('/api/materi/'+materi.id).then(sukses, gagal);
    function sukses(response){
       $scope.listMateri();
    }
    function gagal(response){
       console.log(response);
       alert('Error : '+response);
    };
   };

  $scope.listMateri = function(){
    $http.get('/api/materi').then(sukses,gagal);

    function sukses(response){
      $scope.daftarMateri = response.data;
    };

    function gagal(response){
      console.log(response);
      alert('Error'+response);
    };
  };
  $scope.listMateri();
});
