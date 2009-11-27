(:
 Fabric3
 Copyright © 2008 Metaform Systems Limited

 This proprietary software may be used only connection with the Fabric3 license
 (the “License”), a copy of which is included in the software or may be
 obtained at: http://www.metaformsystems.com/licenses/license.html.

 Software distributed under the License is distributed on an “as is” basis,
 without warranties or conditions of any kind.  See the License for the
 specific language governing permissions and limitations of use of the software.
 This software is distributed in conjunction with other software licensed under
 different terms.  See the separate licenses for those programs included in the
 distribution for the permitted and restricted uses of such software.
:)
(:
    $Rev$ $Date$
:)

declare namespace advService="sca:service:AdvancedService";
declare namespace advReference="sca:reference:AdvancedReference";
declare namespace value="sca:property:value";
declare namespace adv="http://www.example.org/adv";

declare variable $value:value external;


(:
declare function advService:xmlStream($doc){

}
:)

declare function advService:xmlDoc($doc){
    $doc/child[1]
};

declare function advService:xmlNode($node ){
    $node/child[2]
};

declare function advService:xmlNodeList($list){
   for $a in $list
     return $a
(:
    $list

     for $a in $list
     let $b := $a
     return
         $b
:)
};

declare function advService:primativeBoolean($boo){
    xs:boolean($boo)
};

declare function advService:primativeDouble($dbl){
    xs:double($dbl)
};

declare function advService:primativeFloat($fl){
    xs:float($fl)
};

declare function advService:primativeInteger($in){
    xs:integer($in)
};

declare function advService:primativeShort($srt){
    xs:integer($srt)
};

(:
declare function advService:primativeChar($cr){
    xs:string($cr)
};
:)

declare function advService:primativeString($str){
    xs:string($str)
};

declare function advService:primativeVoid(){
    ()
};

declare function advService:primativeNull($nl){
    $nl
};

declare function advService:listArray($list){
    ($list[1],'array2')
};

declare function advService:listList($list){
    ($list[1],'list2')
};

declare function advService:multiParmFunction($str,$lst1,$lst2,$flt,$doc){
  string-join(($str,xs:string($lst1[1]),$lst2[1],xs:string($flt),xs:string($doc/child[1]/@name)),' ') 
};

declare function advService:referenceFunction($val,$list) {
    advReference:reference($val,$list)
};

declare function advService:subFunction($parm) {
    adv:subFunction($parm)
};

declare function adv:subFunction($subParm) {
    string-join(( $subParm,$subParm),' ') 
};

(:
declare function advService:varFunction() {
    xs:string($value:value/value[1]/@name)
};
:)

declare function adv:advFunction($subParm) {
    <adv:advFunction name="{string-join(($subParm,$value:value),' ')}"/>
};


<adv:body>
  {adv:advFunction('service')}
</adv:body>

