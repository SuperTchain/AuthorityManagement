layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , $ = layui.$
        , laydate = layui.laydate;

    // //创建一个编辑器
    // var editIndex = layedit.build('LAY_demo_editor');
    //
    //自定义验证规则
    form.verify({
        title: function (value) {
            if (value.length < 5) {
                return '最小长度为5哦';
            }
        }
        , pass: [
            // /^[\S]{6,12}$/
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/
            // ,'密码必须8到12位，且不能出现空格'
            , '至少8个字符，至少1个大写字母，1个小写字母和1个数字'
        ]
        , account: [
            /^[a-zA-Z0-9_-]{4,16}$/
            , '至少4位，至多16位,不包含特殊字符哦'
        ]
        , age: [
            /^(?:[1-9]?\d|100)$/
            , '只能在1-99岁之间哦'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });


    //监听submit提交
    //submit(submit_video):他是submit按钮的lay-filter取值
    form.on('submit(submit_user)', function (data) {
        console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //         //刷新父窗口
        window.parent.location.reload();
    });

    // //表单赋值
    // layui.$('#LAY-component-form-setval').on('click', function(){
    //     form.val('example', {
    //         "username": "贤心" // "name": "value"
    //         ,"password": "123456"
    //         ,"interest": 1
    //         ,"like[write]": true //复选框选中状态
    //         ,"close": true //开关状态
    //         ,"sex": "女"
    //         ,"desc": "我爱 layui"
    //     });
    // });
    //
    // //表单取值
    // layui.$('#LAY-component-form-getval').on('click', function(){
    //     var data = form.val('example');
    //     alert(JSON.stringify(data));
    // });

});