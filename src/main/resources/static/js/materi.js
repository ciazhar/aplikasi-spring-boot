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
