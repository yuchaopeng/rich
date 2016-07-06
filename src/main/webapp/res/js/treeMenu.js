$(function(){
     $('#treeMenu').tree({
          url:'/menu/getTreeMenus',
          onClick:function(node){
        	  var url = node.attributes.url;
        	  var f = $('#treeMenu').tree('isLeaf',node.target);
        	  if(f){
        		  if(url == null || url == ""){
            		  alertMessage("提示","此功能正在开发中，敬请等待更新.");
            		  return;
            	  }
        		  var url = getRootPath()+url;
            	  addTab(node.id,node.text,url);
        	  }
          }
     });
});