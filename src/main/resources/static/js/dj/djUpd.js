var flag;
var flag1;
var flag2;
var flag3;
var did;
var dname;
var djf;
var dmoneyBl;
var dzk;
var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
var number=/^[0-9]*$/;
$(function() {
	did=$("#did").val();
	dname=$("#dname").val();
	djf=$("#djf").val();
	dmoneyBl=$("#dmoneyBl").val();
	dzk=$("#dzk").val();
	//获取焦点
	$("#dname").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
	
});

/*****失去焦点事件******/
function getBlur() {
	$("#dname").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员名称不能为空！','#dname',{tips:[2,'red']});
		}else if (!zu.test(t)) {
			layer.tips('会员名称'+'格式有误！','#dname',{tips:[2,'red']});
		}else if(dname!=t) {
			
			$.ajax({
				url:"dj.do?method=getCkName",
				data:{"dname":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此会员名称已经存在！','#dname',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}else{
			flag=0;
		}
	});
	$("#djf").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员积分不能为空！','#djf',{tips:[2,'red']});
		}else if (!number.test(t)) {
			layer.tips('会员积分'+'格式有误！','#djf',{tips:[2,'red']});
		}else if(djf!=t){
			
			$.ajax({
				url:"dj.do?method=getCkName",
				data:{"djf":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此会员积分已经存在！','#djf',{tips:[2,'red']});
		    	    	     flag1=1;  //标识位!!!
					}else {
						flag1=0;
					}
				}
			});
		}else{
			flag1=0;
		}
	});
	$("#dmoneyBl").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员比例不能为空！','#dmoneyBl',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('会员比例'+'格式有误！','#dmoneyBl',{tips:[2,'red']});
		}else if(dmoneyBl!=t){
			
			$.ajax({
				url:"dj.do?method=getCkName",
				data:{"dmoneyBl":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此会员比例已经存在！','#dmoneyBl',{tips:[2,'red']});
		    	    	     flag2=1;  //标识位!!!
					}else {
						flag2=0;
					}
				}
			});
		}else{
			flag2=0;
		}
	});
	$("#dzk").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，会员折扣不能为空！','#dzk',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('会员折扣'+'格式有误！','#dzk',{tips:[2,'red']});
		}else if(dzk!=t){
			
			$.ajax({
				url:"dj.do?method=getCkName",
				data:{"dzk":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此会员折扣已经存在！','#dzk',{tips:[2,'red']});
		    	    	     flag3=1;  //标识位!!!
					}else {
						flag3=0;
					}
				}
			});
		}else{
			flag3=0;
		}
	});
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var did=$("#did").val();
		var dname=$("#dname").val();
		var djf=$("#djf").val();
		var dmoneyBl=$("#dmoneyBl").val();
		var dzk=$("#dzk").val();
		if (dname.length==0) {
			 layer.tips('对不起，会员名称不能为空！','#dname',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，会员名称已存在！','#dname',{tips:[2,'red']});
		}else if (!zu.test(dname)) {
			layer.tips('会员名称'+'格式有误！','#dname',{tips:[2,'red']});
		}else if (djf.length==0) {
			 layer.tips('对不起，会员积分不能为空！','#djf',{tips:[2,'red']});
		}else if (flag1==1) {
			layer.tips('对不起，会员积分已存在！','#djf',{tips:[2,'red']});
		}else if (!number.test(djf)) {
			layer.tips('会员积分'+'格式有误！','#djf',{tips:[2,'red']});
		}else if (dmoneyBl.length==0) {
			 layer.tips('对不起，会员比例不能为空！','#dmoneyBl',{tips:[2,'red']});
		}else if (flag2==1) {
			layer.tips('对不起，会员比例已存在！','#dmoneyBl',{tips:[2,'red']});
		}else if (!sz.test(dmoneyBl)) {
			layer.tips('会员比例'+'格式有误！','#dmoneyBl',{tips:[2,'red']});
		}else if (dzk.length==0) {
			 layer.tips('对不起，会员折扣不能为空！','#dzk',{tips:[2,'red']});
		}else if (flag3==1) {
			layer.tips('对不起，会员折扣已存在！','#dzk',{tips:[2,'red']});
		}else if (!sz.test(dzk)) {
			layer.tips('会员折扣'+'格式有误！','#dzk',{tips:[2,'red']});
		}else {
			var xx="dname="+dname+"&djf="+djf+"&dmoneyBl="+dmoneyBl+"&dzk="+dzk+"&did="+did;
			$.post("dj.do?method=djUpd",xx,function(mydata){
				if (mydata==true) {
					//增加成功！
					   layer.msg('修改成功！', {icon : 1,time : 60000});
		    		       setTimeout(function() {
		    		    	  var index = parent.layer.getFrameIndex(window.name); 
			    		      parent.layer.close(index);
		    		    	},
		    		    	2000);
		    		   }
		       },'json');
		}
	});
}
