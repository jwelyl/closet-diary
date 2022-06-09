let main = {
    init : function () {
        let _this = this;
        $('#btn-save-picture').on('click', function () {
            _this.save_picture();
        });

        $('#btn-update-picture').on('click', function () {
            _this.update_picture();
        });

        $('#btn-delete-picture').on('click', function () {
            _this.delete_picture();
        });

        $('#btn-save-clothes').on('click', function () {
            _this.save_clothes();
        });

        $('#btn-update-clothes').on('click', function () {
            _this.update_clothes();
        });

        $('#btn-delete-clothes').on('click', function () {
            _this.delete_clothes();
        });

        $('#btn-save-member').on('click', function () {
            _this.save_member();
        });

        $('#btn-update-member').on('click', function () {
            _this.update_member();
        });

        $('#btn-delete-member').on('click', function () {
            _this.delete_member();
        });

    },
    save_picture : function () {
        console.log("save_picture start!");
        let data = {
            originFileName : $('#origin-file-name').val(),
            fileName : $('#file-name').val(),
            filePath : $('#file-path').val(),
            fileSize : $('#file-size').val()
        };
        console.log("0origin_name :", data.originFileName);
        console.log("0name :", data.fileName);
        console.log("0path :", data.filePath);
        console.log("0size :", data.fileSize);

        $.ajax({
            type : 'POST',
            url : '/api/v1/picture',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            // console.log("1picture_name :", data.picture_name);
            // console.log("1picture_path :", data.picture_path);
            // console.log("1picture_size :", data.picture_size);
            alert('사진이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update_picture : function () {
        let data = {
            originFileName: $('#origin-file-name').val(),
            fileName: $('#file-name').val(),
            filePath: $('#file-path').val(),
            fileSize: $('#file-size').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/picture/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('사진 정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete_picture : function () {
        let id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/picture/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('사진 정보가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


    save_clothes : function () {
        console.log("save_clothes start!");
        let data = {
            name : $('#name').val(),
            category : $('#category').val(),
            brand : $('#brand').val(),
            purchaseDate : $('#purchase-date').val(),
            price : $('#price').val(),
            originPicName : $('#origin-pic-name').val(),
            picPath : $('#pic-path').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/clothes',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            // console.log("1picture_name :", data.picture_name);
            // console.log("1picture_path :", data.picture_path);
            // console.log("1picture_size :", data.picture_size);
            alert('옷이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update_clothes : function () {
        let data = {
            name : $('#name').val(),
            category : $('#category').val(),
            brand : $('#brand').val(),
            purchaseDate : $('#purchase-date').val(),
            price : $('#price').val(),
            originPicName : $('#origin-pic-name').val(),
            picPath : $('#pic-path').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/clothes/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('옷 정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete_clothes : function () {
        let id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/clothes/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('옷 정보가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    save_member : function () {
        console.log("save_member start!");
        let data = {
            name : $('#member-name').val(),
            age : $('#member-age').val(),
            address : $('#member-address').val()
        };

        console.log(JSON.stringify(data));

        $.ajax({
            type : 'POST',
            url : '/api/v1/member',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('멤버가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update_member : function () {
        let data = {
            name: $('#member-name').val(),
            age: $('#member-age').val(),
            address: $('#member-address').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/member/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('멤버 정보가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete_member : function () {
        let id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/member/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('멤버 정보가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();