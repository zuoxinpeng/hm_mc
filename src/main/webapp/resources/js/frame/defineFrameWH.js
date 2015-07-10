/**
 * Created with JetBrains PhpStorm.
 * User: YK
 * Date: 14-10-17
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 */
function computeWH(tar){
    var tar=$("#sub_wrap"), wid=tar.width(),hg=tar.get(0);

}
function shutFloatDivEvent(shutClass){
    var shutC=shutClass||".shut";
       $(shutC).click(function(){
           parent.shutFloatDiv();
      })
}
