<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/skin/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript">
        var setting = {};
        var nodes = [
            {name : "教科书",open:true,
                children:[
                    {name : "物理",open:true,
                        children:[
                            {name:"初中物理"},
                            {name:"高中物理"},
                            {name:"大学物理"}
                        ]
                    },
                    {name:"数学",
                        children:[
                            {name:"几何"},
                            {name:"概率论"},
                            {name:"代数"}
                        ]
                    }
                ]
            },
            {name : "名著",
                children:[
                    {name : "国外",open:true,
                        children:[
                            {name:"战争与和平"},
                            {name:"安娜"},
                            {name:"童年"}
                        ]
                    },
                    {name:"国内",
                        children:[
                            {name:"桑凯凯作品集"},
                            {name:"三国演义"},
                            {name:"封神演义"},
                            {name:"聊斋"}
                        ]
                    }
                ]
            },
            {name:"网络小说",
                children:[
                    {name:"玄幻",
                        children:[
                            {name:"斗罗大陆"},
                            {name:"斗破苍穹"}
                        ]
                    },
                    {name:"仙侠",
                        children:[
                            {name:"凡人修仙传"},
                            {name:"仙逆"}
                        ]
                    }
                ]
            }
        ];

        $(document).ready(function(){
            $.fn.zTree.init($("#treedemo"), setting, nodes);
        });
    </script>
</head>
<body>

<div style="width: 200px;height: 500px" class="zTreeDemoBackground left">
    <ul id="treedemo" class="ztree"></ul>
</div>

</body>
</html>
