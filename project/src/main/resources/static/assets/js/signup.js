//정규식 모음
let email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
let korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
let phoneNum = /^[0-9]{3}[0-9]{3,4}[0-9]{4}/;
//특수문자 필터
let specialChar = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
//공백 피터
let blank = /[\s]/g;
//한글만 필터
let onlyKorean = /[\s]|[A-Za-z0-9]|[ \[\]{}()<>?_-]|[`~!@#$%^&*|\\\'\";+=,.:\/?]/g;
// 영문 or 숫자만 필터
let onlyEngNum = /[\s]|[A-Z]|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[\{\}\[\]\/?,;:|\)*~`!^\-_+<>\#$%&\\\=\(\'\"]/;
// 숫자만 필터
let onlyNum = /[\s]|[a-zA-Z]|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[\{\}\[\]\/?,;:|\)*~`!^\-_+<>\#$%&\\\=\(\'\"]/;

//아이디 체크 여부 확인 (true면 했음)
let idchapply = $('#idck').is(':checked');


$('#memId').val('');
$('#phoneNo').val('');

//아이디 이메일형식 체크, 소문자,숫자만 입력
$('#memId').on('propertychange change keyup paste input', function (e) {
    $(e.target).val($(e.target).val().replace(onlyEngNum, ''));
    let intext = $(e.target).val();

    if (email.test(intext)) {
        $(e.target).prop("class", "form-control is-valid");
    } else if (intext.length == 0) {
        $(e.target).prop("class", "form-control is-invalid");
        $('#memIdFeedback').text(``);
    } else {
        $(e.target).prop("class", "form-control is-invalid");
        $('#memIdFeedback').text(`이메일형식이 올바르지 않습니다.`);
    }
})

//중복확인
$('#duplicateBtn').on('click', function (e) {
    let memId = $('#memId').val();
    if (memId.length < 1) {
        Swal2.fire({
            icon: "error",
            title: "아이디를 입력해주세요",
        })
        return;
    }
    if (!email.test(memId)) {
        Swal2.fire({
            icon: "error",
            title: "이메일 형식으로 아이디를 입력해주세요",
        })
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'idcheck',
        data: { memId: memId },
        dataType: 'json',
        success: function (result) {
            if (result) {
                $('#idck').prop('checked', true);
                Swal2.fire({
                    icon: "success",
                    title: "가입 가능한 아이디입니다",
                })
            } else {
                $('#idck').prop('checked', false);
                Swal2.fire({
                    icon: "error",
                    title: "아이디가 중복 됩니다",
                })
            }
        },
        error: () => console.log(error)
    })
})

//이름 한글만 입력되도록
$('#name').on('propertychange change keyup paste input', function (e) {

    $(e.target).val($(e.target).val().replace(onlyKorean, ''));

    let intext = $(e.target).val();

    if (intext.length == 0) {
        $(e.target).prop("class", "form-control is-invalid")
    } else if (intext.length > 1) {
        $(e.target).prop("class", "form-control is-valid");
    } else {
        $(e.target).prop("class", "form-control is-invalid")
    }
})

//닉네임 특수문자만 막음
$('#nick').on('propertychange change keyup paste input', function (e) {

    $(e.target).val($(e.target).val().replace(specialChar, ''));

    let intext = $(e.target).val();

    if (intext.length == 0) {
        $(e.target).prop("class", "form-control is-invalid")
    } else if (intext.length > 1) {
        $(e.target).prop("class", "form-control is-valid");
    } else {
        $(e.target).prop("class", "form-control is-invalid")
    }
})

//비밀번호 8자리이상, 공백막음
$('#pwd').on('propertychange change keyup paste input', function (e) {

    $(e.target).val($(e.target).val().replace(blank, ''));

    let intext = $(e.target).val();

    if (intext.length == 0) {
        $(e.target).prop("class", "form-control is-invalid")
    } else if (intext.length >= 8) {
        $(e.target).prop("class", "form-control is-valid");
    } else {
        $(e.target).prop("class", "form-control is-invalid")
    }
})

//폰넘버 숫자만 입력, 공백 막음
$('#phoneNo').on('propertychange change keyup paste input', function (e) {

    $(e.target).val($(e.target).val().replace(onlyNum, ''));

    let intext = $(e.target).val();

    if (phoneNum.test(intext)) {
        $(e.target).prop("class", "form-control is-valid");
    } else if (intext.length == 0) {
        $(e.target).prop("class", "form-control is-invalid");
    } else {
        $(e.target).prop("class", "form-control is-invalid");
    }
})
//비밀번호 보기
$(document).ready(function () {
    $("#pwview").on("click", function (e) {
        $("#pwview").toggleClass("active");

        if ($("#pwview").hasClass("active")) {
            $("#pwview > i").attr("class", "fa-regular fa-eye");
            $("#pwd").attr("type", "text");
        } else {
            $("#pwview > i").attr("class", "fa-regular fa-eye-slash");
            $("#pwd").attr("type", "password");
        }
    });
    if ($('#message').val() != '') {
        Toast.fire({
            icon: "error",
            title: $('#message').val(),
        })
    }
    $('#hostForm').hide();


    $('#submitBtn').on('click', function (e) {
        // e.preventDefault();
        // if ($('#phoneCk').is(':checked') && $('#idck').is(':checked')) {
        //     $('form').submit();
        // } else if (!$('#idck').is(':checked')) {
        //     Swal2.fire({
        //         icon: "error",
        //         title: "아이디 중복 확인이 필요합니다.",
        //     })
        // } else {
        //     Swal2.fire({
        //         icon: "error",
        //         title: "본인 인증이 필요합니다.",
        //     })
        // }
    })

    IMP.init("imp48072683");
    $('#phoneNoBtn').on('click', function (e) {
        if ($('#phoneNo').val() == '') {
            Swal2.fire({
                icon: "error",
                title: "번호를 입력 해 주세요.",
            })
            return;
        }
        IMP.certification({
        }, function (rsp) {
            if (rsp.success) {
                let impUid = rsp.imp_uid;
                Swal2.fire({
                    icon: "success",
                    title: "본인 인증이 완료되었습니다.",
                })
                $('#phoneCk').prop('checked', true);
            } else {
                console.log("실패")
            }
        });
    })

    $('#forgotphoneNoBtn').on('click', function (e) {

        if ($('#phoneNo').val() == '') {
            Swal2.fire({
                icon: "error",
                title: "번호를 입력 해 주세요.",
            })
            return;
        }

        IMP.certification({
        }, function (rsp) {
            if (rsp.success) {
                Swal2.fire({
                    icon: "success",
                    title: "본인 인증이 완료되었습니다.",
                })
                $('#phoneCk').prop('checked', true);

                let phoneNo = $('input[name="phoneNo"]').val();

                $.ajax({
                    type: 'POST',
                    url: 'forgotid',
                    contentType: 'application/json',
                    data: JSON.stringify({ phoneNo: phoneNo }),
                    success: function (result) {
                        console.log(result);
                        $('#myId').prop('hidden', false);
                        $('#memId').val(result);
                        Swal2.fire({
                            icon: "success",
                            title: "아이디 찾기 완료",
                        })
                    }
                    ,
                    error: () => console.log(error)
                })
            } else {
                Swal2.fire({
                    icon: "error",
                    title: "본인 인증 실패",
                })
            }
        });




    })

    $('#forgotpwphoneNoBtn').on('click', function (e) {
        if ($('#phoneNo').val() == '') {
            Swal2.fire({
                icon: "error",
                title: "번호를 입력 해 주세요.",
            })
            return;
        }

        IMP.certification({
        }, function (rsp) {
            if (rsp.success) {

                Swal2.fire({
                    icon: "success",
                    title: "본인 인증이 완료되었습니다.",
                })
                $('#phoneCk').prop('checked', true);
                $('#myPw').prop('hidden', false);
            } else {
                console.log("실패")
            }
        });
    })

    $('#pwUpdate').on('click', function (e) {
        let phoneNo = $('input[name="phoneNo"]').val();
        let memId = $('input[name="memId"]').val();
        let pwd = $('input[name="pwd"]').val();
        $.ajax({
            type: 'POST',
            url: 'forgotpw',
            contentType: 'application/json',
            data: JSON.stringify({ phoneNo, memId, pwd }),
            success: function (result) {
                if (result > 0) {
                    Swal2.fire({
                        icon: "success",
                        title: "비밀번호 수정 완료",
                    }).then(result => {
                        location.href = "/loginform";
                    })
                } else {
                    Swal2.fire({
                        icon: "error",
                        title: "비밀번호 수정 실패",
                    })
                }
            },
            error: () => console.log(error)
        })
    })



});

//멤버 호스트 메뉴 전환
$('input:radio[name=memDiv]').on("change", function (e) {
    let ckId = $(e.target).prop('id');

    if (ckId == 'member') {
        location.href = "signup";
    } else {
        location.href = "hostsignup";
    }
});


$('#crtroomBtn').on('click', function (e) {
    let inputVal = $('#crtroomName').val();
    $('#searchcrt').addClass('show');
    $.ajax({
        type: 'get',
        url: 'searcharea',
        data: { inputVal },
        success: function (result) {
            $('#searchCrtItem').empty();
            $('#searchCrtItem').append('<option selected="">코트선택</option>')
            $.each(result, function (index, data) { // 데이터 =item
                let b = `													 
					<option value="${data.crtroomNo}">${data.crtroomName}</option>`
                $('#searchCrtItem').append(b)
            });
            b = '</optgroup>'
            $('#searchCrtItem').append(b)
        },
        error: () => console.log(error)
    })

    if (inputVal == '') {
        $('#searchcrt').removeClass('show');
    }


})

$('#searchCrtItem').on('change', function (e) {
    let crtNo = $('#searchCrtItem option:selected').val();
    let crtName = $('#searchCrtItem option:selected').text();
    console.log(crtName)
    if (crtNo != null) {
        $('#crtroomNo').val(crtNo);
        $('#crtroomName').val(crtName);
        $('#searchcrt').removeClass('show');
    }
})


