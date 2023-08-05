var flag;
var zu=/^[\u0391-\uFFE5]+$/;
var sz=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
$(function() {
	layui.use(function() {
		var form = layui.form,
		laydate=layui.laydate;
		laydate.render({ 
			  elem: '#ybegintime',
			  format: 'yyyy-MM-dd' //可任意组合
			});
		laydate.render({ 
			  elem: '#yendtime',
			  format: 'yyyy-MM-dd' //可任意组合
			});
	});
	//获取焦点
	$("#ytitle").focus();
	//失去焦点
	getBlur();
	//提交
	getBtn();
	
});

/*****失去焦点事件******/
function getBlur() {
	$("#ytitle").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，活动名称不能为空！','#ytitle',{tips:[2,'red']});
		}else {
			
			$.ajax({
				url:"youhui.do?method=getCkName",
				data:{"ytitle":t},
				dataType:'json',
				type:'post',
				success:function(mydata){
					if (mydata==1) {
						   layer.tips('对不起，此活动名称已经存在！','#ytitle',{tips:[2,'red']});
		    	    	     flag=1;  //标识位!!!
					}else {
						flag=0;
					}
				}
			});
		}
	});
	$("#ymoney").blur(function() {
		var t=$(this).val();
		if (t.length==0) {
			 layer.tips('对不起，优惠金额不能为空！','#ymoney',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('优惠金额'+'格式有误！','#ymoney',{tips:[2,'red']});
		}
	});
	$("#ylessmoney").blur(function() {
		var t=$(this).val();
		var ymoney=$("#ymoney").val();
		if (t.length==0) {
			 layer.tips('对不起，起始金额不能为空！','#ylessmoney',{tips:[2,'red']});
		}else if (!sz.test(t)) {
			layer.tips('起始金额'+'格式有误！','#ylessmoney',{tips:[2,'red']});
		}else if (Number(t)<Number(ymoney)) {
			layer.tips('起始金额不能小于优惠金额！','#ylessmoney',{tips:[2,'red']});
		}
	});
}

/********提交*****************/
function getBtn() {
	$("#btn").click(function() {
		var ytitle=$("#ytitle").val();
		var ybegintime=$("#ybegintime").val();
		var yendtime=$("#yendtime").val();
		var ymoney=$("#ymoney").val();
		var ylessmoney=$("#ylessmoney").val();
		var millSecsDiff = new Date(yendtime).getTime() - new Date(ybegintime).getTime();
		var daysDiff = Math.floor(millSecsDiff / (24 * 3600 * 1000));
		if (ytitle.length==0) {
			 layer.tips('对不起，活动名称不能为空！','#ytitle',{tips:[2,'red']});
		}else if (flag==1) {
			layer.tips('对不起，活动名称已存在！','#ytitle',{tips:[2,'red']});
		}else if (ybegintime.length==0) {
			 layer.tips('对不起，开始时间不能为空！','#ybegintime',{tips:[2,'red']});
		}else if (yendtime.length==0) {
			 layer.tips('对不起，结束时间不能为空！','#yendtime',{tips:[2,'red']});
		}else if (daysDiff<1) {
			 layer.tips('结束时间不能小于开始时间！','#yendtime',{tips:[2,'red']});
		}else if (ymoney.length==0) {
			 layer.tips('对不起，优惠金额不能为空！','#ymoney',{tips:[2,'red']});
		}else if (!sz.test(ymoney)) {
			layer.tips('优惠金额'+'格式有误！','#ymoney',{tips:[2,'red']});
		}else if (ylessmoney.length==0) {
			 layer.tips('对不起，初始金额不能为空！','#ylessmoney',{tips:[2,'red']});
		}else if (!sz.test(ylessmoney)) {
			layer.tips('初始金额'+'格式有误！','#ylessmoney',{tips:[2,'red']});
		}else if (Number(ylessmoney)<Number(ymoney)) {
			layer.tips('起始金额不能小于优惠金额！','#ylessmoney',{tips:[2,'red']});
		}else {
			var xx="ytitle="+ytitle+"&ybegintime="+ybegintime+"&yendtime="+yendtime+"&ymoney="+ymoney+"&ylessmoney="+ylessmoney;
			$.post("youhui.do?method=youhuiAdd",xx,function(mydata){
				if (mydata==true) {
					//增加成功！
					   layer.msg('添加成功！', {icon : 1,time : 60000});
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
