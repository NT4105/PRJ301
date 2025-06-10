<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo</title>
    </head>
    <body>
        <h1>JSP Life Cycle Demo</h1>
        <%!
            int num;

            public void jspInit() {
                System.out.println("jspInit is invoked!!!");
                num = 10;
            }

            public void jspDestroy() {
                System.out.println("jspDestroy is invoked!!!");
            }

            public int add(int n) {
                System.out.println("add is called!!!");
                num += n;
                return num;
            }
        %>

        Init number: <%= num %><br/>
        <% out.println("Result of add is " + add(5)); %> 
    </body>
</html>
