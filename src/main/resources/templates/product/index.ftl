<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <!--边栏-->
    <#include "../common/nav.ftl">
    <!--主要内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                        </div>
                        <div class="col-md-4 column">
                            <form role="form">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label><input type="email" class="form-control" id="exampleInputEmail1" />
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label><input type="password" class="form-control" id="exampleInputPassword1" />
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile" />
                                    <p class="help-block">
                                        Example block-level help text here.
                                    </p>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" />Check me out</label>
                                </div> <button type="submit" class="btn btn-default">Submit</button>
                            </form>
                        </div>
                        <div class="col-md-4 column">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>