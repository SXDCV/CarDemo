var flag;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
var number=/^[0-9]*$/;
$(function() {
	layui.use(function() {
		var form = layui.form;
	});
	//获取焦点
	$("#nname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});



/*****失去焦点事件******/
function getBlur() {
	$("#nname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，礼品名称不能为空！','#nname',{tips:[2,'red']});
		}else{
			$.ajax({
				url:"lipin.do?method=getCkName",
				data:{"nname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					
					if (mydata==1) {
						   layer.tips('对不起，此礼品已经存在！','#nname',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}
	});
	$("#njf").blur(function() {
		var t=$(this).val();
		if (t.length==0||t==0) {
			 layer.tips('对不起，兑换积分不能为空！','#njf',{tips:[2,'red']});
		}else if (!number.test(t)) {
			layer.tips('兑换积分'+'格式有误！','#njf',{tips:[2,'red']});
		}
	});
	$("#ncount").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，进货数量不能为空！','#ncount',{tips:[2,'red']});
		}else if (!number.test(t)) {
			layer.tips('进货数量'+'格式有误！','#ncount',{tips:[2,'red']});
		}else if (t==0) {
			layer.tips('对不起，进货数量不能为0！','#ncount',{tips:[2,'red']});
		}
	});
}


/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var nname=$("#nname").val();
		var njf=$("#njf").val();
		var ncount=$("#ncount").val();
		var bimg=$("#bimg").val();
		
		if (nname.length==0) {
			 layer.tips('对不起，礼品名称不能为空！','#nname',{tips:[2,'red']});
		}else if (flag==1) {
			 layer.tips('对不起，此礼品已经存在！','#nname',{tips:[2,'red']});
		}else if (njf.length==0) {
			 layer.tips('对不起，兑换积分不能为空！','#njf',{tips:[2,'red']});
		}else if (!number.test(njf)) {
			layer.tips('兑换积分'+'格式有误！','#njf',{tips:[2,'red']});
		}else if (njf==0) {
			 layer.tips('对不起，兑换积分不能为0！','#njf',{tips:[2,'red']});
		}else if (ncount.length==0) {
			 layer.tips('对不起，进货数量不能为空！','#ncount',{tips:[2,'red']});
		}else if (!number.test(ncount)) {
			layer.tips('进货数量'+'格式有误！','#ncount',{tips:[2,'red']});
		}else if (ncount==0) {
			layer.tips('对不起，进货数量不能为0！','#ncount',{tips:[2,'red']});
		}else {
			$.ajaxFileUpload({
				url:"lipin.do?method=lipinAdd",
				secureuri:false,
				fileElementId:['bimg'],
				data:{'nname':nname,'njf':njf,'ncount':ncount},
				success:function(mydata,status){
					parent.layer.msg('添加成功!',{icon:6,time:3000});
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				},
				error:function(mydata,status,e){
					parent.layer.msg('添加失败!',{icon:6,time:3000});
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		}
	});
}
