<%@ page import="com.fiesta.model.vo.Comcategory"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.fiesta.model.dao.CompanyDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	ArrayList<Comcategory> comCategory = new ArrayList<>();
	comCategory = CompanyDaoImpl.getInstance().showAllComcategory();
	request.setAttribute("comCategory", comCategory);
	
	String[] categoryIcon = {"supervisor_account", "hotel", "local_bar", "directions_bus", "headset", 
			                 "business_center", "local_dining", "accessibility", "image_aspect_ratio", "attach_money"};
	request.setAttribute("categoryIcon", categoryIcon);
%>  	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fiesta</title>
    <link href="resource/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="10">

    <style>
        #requestFiesta {
            background-image: url('https://source.unsplash.com/collection/10914958/1600x900');
            background-size: cover;
            position: relative;
            min-height:90vh;
        }
        #requestFiesta .blackBox {
            background: black;
            height: 100%;
            opacity: 50%;
            min-height:90vh;
        }
        #requestFiesta .contentBox {
            color: white;
            position: absolute;
            top: 92px;
            bottom: 0;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #requestFiesta .contentBox h2 {
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
        }
        #requestFiesta .comCategoryList {
            width: 600px;
            display: flex;
            flex-wrap : wrap;
            justify-content: center;
        }
        #requestFiesta .comCategoryList a {
            color: white;
            display: block;
            margin: 10px 20px;
        }
        #requestFiesta .comCategoryList a:hover {
        	color: #009688;
        }
        #requestFiesta .comCategoryList a p {
        	text-align: center;
            font-weight: bold;
        }
        
        /* responsive */
        @media (max-width: 600px) {
        	#requestFiesta .comCategoryList {
        		width: auto;
        	}
        }
    </style>
</head>
<body>
    <!-- header import -->
    <c:import url="header.jsp" charEncoding="UTF-8"></c:import>
    
    <!-- 항상 section에서 시작 -->
    <!-- 고객 의뢰 -->
	<section id="requestFiesta"> 
        <div class="blackBox"></div>
        <div class="contentBox">
            <div>
                <h2>딱! 맞는 업체를 소개해드립니다</h2>
                <div class="comCategoryList">
	                <c:forEach items="${comCategory}" var="category" varStatus="status">
	                	<a href="request.do?requestFiesta=${category.comCategoryCode}">
	                		<i class="medium material-icons">${categoryIcon[status.index]}</i>
	                    	<p>${category.comCategoryDesc}</p>
	                	</a>
	                </c:forEach>
                </div>
            </div>
        </div>    
    </section>
    <!-- <a href="customer/test.jsp">고객의뢰 테스트</a> -->
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Earum facilis ducimus consequatur eum a voluptates unde dignissimos, quas architecto eligendi maiores totam iure minus ipsa quibusdam! Eius vel reiciendis corporis?
    Aspernatur exercitationem id quos debitis ex similique ratione ducimus a iusto quis vero tempore sunt nulla sint consectetur, consequatur sapiente? Dolor, eum explicabo ipsam veniam iusto distinctio quas quis excepturi.
    Culpa consectetur sequi illo, incidunt nostrum ipsam dolores rem neque placeat debitis accusantium mollitia ducimus labore quas animi sit? Placeat est officiis, quae alias odit similique minus. Magni, quasi voluptates?
    Fuga adipisci architecto numquam ipsum, suscipit veritatis repellendus placeat voluptate? Quasi dolore quam consequuntur eveniet suscipit officiis nam accusamus, eum debitis sit repellat commodi. Quibusdam unde eos magni nisi eligendi?
    Minus perspiciatis enim sint laudantium blanditiis quo aspernatur ipsa! Nemo optio aperiam error. Molestiae minus iusto maxime voluptatum odit quibusdam autem in. Incidunt sint consequatur odio nobis beatae, doloremque eaque?
    Sed sit sunt rem vel quia labore possimus iusto quasi voluptatum repellat adipisci animi ratione, suscipit, dolores recusandae deserunt quos corporis minus quae hic saepe? Quam obcaecati ut rerum eligendi.
    Vero assumenda distinctio voluptas nisi. Beatae sunt deleniti aperiam natus, in unde, nisi voluptates deserunt officia incidunt molestiae repudiandae architecto rem laborum numquam quidem! Unde perspiciatis quisquam repellendus qui dicta?
    Itaque, molestiae ducimus vel aliquam, deleniti nesciunt laudantium omnis, cum quo ipsam aut aspernatur adipisci beatae et aliquid magnam impedit recusandae accusamus ut explicabo. Nihil quos nobis eveniet maiores facere.
    Nesciunt atque cupiditate debitis rerum odio unde iure cumque, mollitia dolores. Culpa et quis ab ullam soluta quam id nam expedita. Dolore quibusdam aliquid sapiente praesentium ipsa, officiis atque ipsum!
    Amet magni cum dolor vero eius maiores possimus quidem, corporis voluptatum corrupti nulla nostrum, placeat error fuga explicabo natus numquam illum deleniti veritatis. Ipsum dolor aperiam repudiandae delectus, alias dolore!
    Quis assumenda necessitatibus mollitia ipsam tenetur ex, sequi qui obcaecati hic repudiandae enim rem ea corrupti eligendi nostrum! Laborum suscipit fuga veritatis maiores temporibus ad odio repellendus? Exercitationem, odio numquam.
    Hic quae aliquid distinctio commodi ab numquam suscipit fuga quis, quaerat tempora, soluta sequi libero. Tempore voluptatibus optio architecto impedit eum adipisci error ipsam inventore quae, magni enim laboriosam odio.
    Fugiat, hic maiores. Voluptatem, sit. Vero voluptatum veritatis, obcaecati, dolorum in temporibus suscipit officiis a libero, recusandae ut incidunt placeat ea corporis deserunt. Hic iure, iste officia veritatis debitis ab.
    Illum tenetur blanditiis quo inventore assumenda, iusto laborum. Laboriosam cumque fuga dolorem? Nihil velit quos commodi repudiandae quo totam quia, consectetur fuga. Ratione illum ullam totam sapiente neque perferendis mollitia.
    Aliquam officia tenetur accusantium ab sequi nobis consequuntur molestias, explicabo consectetur, soluta expedita, voluptatum iste fugiat ea sit adipisci voluptates eveniet in tempore ducimus. Ipsum reiciendis ipsam nulla ex eligendi!
    Sint numquam suscipit debitis quisquam ullam? Unde laudantium aut, quidem quas minima, nobis est quasi minus, autem nulla numquam reiciendis animi perferendis neque dolorem voluptatem dolorum aliquid tenetur enim quaerat?
    Rerum aut maxime, in aspernatur facilis inventore fugiat perferendis ratione nobis autem veniam blanditiis sint, sapiente, deleniti recusandae temporibus earum incidunt? Maiores natus iusto est ex quas facilis explicabo distinctio.
    Ipsum voluptatem amet nam alias ipsa ullam quas possimus impedit inventore ipsam! Error, sint maxime? Iusto architecto beatae labore porro in provident illum consequuntur, ratione iure id aut molestiae laborum.
    Culpa, cum ratione! Laboriosam assumenda iusto quos magnam quod a at iste, sunt laborum, delectus enim. Magnam, nihil illum perferendis maxime laboriosam deleniti. Voluptatum excepturi, omnis dolore et enim error.
    Molestias cumque omnis ipsa voluptate ullam enim itaque ipsum, perferendis, explicabo fugiat ab sequi. Veritatis illum et id dolores beatae nisi aliquam inventore neque officia velit! Exercitationem illo cupiditate sequi.
    Minus distinctio hic ut obcaecati quasi at nemo illo commodi sed placeat, culpa, laudantium blanditiis ducimus magni. Officia facere neque repellat quo aperiam animi necessitatibus modi deserunt, esse fugiat iusto?
    Iusto aperiam inventore dolorem nisi! Minus aliquam cumque delectus! In quam totam quae excepturi consequuntur asperiores nobis itaque, nam repellendus voluptas dolor officia consequatur earum? Consequatur est hic placeat enim!
    Voluptate asperiores corrupti fugiat, modi consequatur ipsa porro recusandae eos alias. Error deleniti earum id autem cupiditate. Corporis at ducimus sit rem ullam, commodi quod accusamus eius. Nemo, hic quidem!
    Minus dicta dolores adipisci. Vel, suscipit consequuntur! Odio quas repudiandae harum atque facere ipsam aspernatur vero tempora dolorem nemo tempore inventore commodi provident sequi repellat reiciendis suscipit, magnam qui necessitatibus.
    Sequi delectus illum iure soluta veritatis, sed quae veniam vero amet facere voluptatum explicabo deserunt esse nam sit est similique possimus. Odio illum tempore aperiam repudiandae tenetur nam consequuntur molestias.
    Rem incidunt quibusdam at a amet officiis, quidem sunt nam natus voluptas commodi optio sequi tempora molestias iusto nemo quisquam iure aperiam blanditiis fugiat odio magni neque? Placeat, perspiciatis vitae?
    Eligendi libero quae ullam distinctio iure, non aspernatur voluptate expedita id quod fugit blanditiis nihil! Beatae cupiditate temporibus labore aliquam facere itaque, minus harum ipsa suscipit quibusdam assumenda non cum.
    Recusandae ipsam architecto sequi consectetur, nisi assumenda aspernatur, repellendus, voluptate nostrum amet sit. Assumenda, aliquid accusamus quidem saepe aliquam, amet facilis, quia earum quaerat ut in error? Cumque, beatae repellat.
    Ea voluptates mollitia obcaecati quis dicta velit distinctio iure optio, numquam consectetur? Similique odit placeat voluptate rem, optio quia! Fugiat nisi fugit debitis, itaque possimus cum unde minus quisquam nostrum.
    Nihil obcaecati aliquid officia? Dolor, nesciunt neque numquam rerum, maiores corrupti aperiam accusamus porro asperiores ullam necessitatibus quae eveniet nobis esse fugit dolore dolorum enim veritatis. Hic minus ipsa ad!
    Earum molestiae quasi dolor natus nam facere labore a, accusantium ratione doloremque, dolorem, voluptatibus nihil illum mollitia hic explicabo temporibus repudiandae quas vitae quidem sequi! Id deleniti quam officiis consequatur.
    Accusamus perferendis consequatur expedita sit, facilis excepturi quis voluptas ducimus? Soluta iste eligendi nesciunt mollitia modi neque molestiae maiores magnam sed? Esse saepe neque odio sequi exercitationem, necessitatibus inventore eaque!
    Voluptatum quam porro qui similique et ipsam ex officiis quia, exercitationem numquam? Quaerat, delectus est! Consequatur eveniet accusantium atque laborum, expedita tempora deserunt, animi dolorum alias beatae iusto perferendis fugiat.
    Rem qui non ipsum ab unde libero quo hic numquam aut, beatae amet dolore eum molestiae esse, accusamus voluptate incidunt perferendis commodi neque nostrum adipisci laborum ratione! Voluptatum, explicabo et!
    Voluptate nesciunt itaque nihil veniam expedita. Fugit nemo nulla eveniet blanditiis dolorum autem id assumenda consequuntur odit dolor. Obcaecati voluptate, earum veritatis dolor ab fugit velit hic! Vero, debitis distinctio!
    Veritatis dolor quas, facilis quo vel ad cupiditate quibusdam, dicta nisi nostrum officiis hic dignissimos ipsam soluta iusto earum tempora natus atque maiores explicabo quod asperiores? Voluptates, voluptas laudantium? Quaerat!
    Esse ea a vero accusantium? Porro asperiores doloribus mollitia officiis expedita, quaerat incidunt architecto quas voluptatum commodi eveniet impedit aliquid ipsum saepe laudantium excepturi suscipit, itaque provident dicta possimus. Eligendi?
    Officiis blanditiis totam enim. Ea, odit nihil praesentium sint, cum, amet possimus voluptatum maiores distinctio cumque consequuntur dolor nisi dolorum impedit ipsam corporis at sed quis sunt alias consectetur labore.
    Molestias, suscipit necessitatibus. Adipisci non, delectus saepe maiores qui voluptatem blanditiis facere praesentium ex? Autem cupiditate magnam eligendi minus excepturi laboriosam quis. Voluptates non dolorum perferendis sit, et at voluptatem.
    Culpa facere aliquam deserunt nostrum quisquam labore non eius id, suscipit dolor? Eum, excepturi ipsam consequatur dolorem nam vel provident quis at repellat porro earum consequuntur explicabo facere. Quidem, non?
    Ratione tempore delectus mollitia libero iusto aliquam cupiditate! Neque cupiditate repellendus, est sapiente nostrum quam aut iste. Sed, inventore blanditiis natus qui mollitia facilis possimus quam commodi numquam ad error.
    Perspiciatis ipsum placeat possimus. Velit voluptas perspiciatis voluptatibus dolorem laboriosam distinctio repellendus dignissimos, possimus dolor exercitationem ipsam quaerat error rerum esse sunt delectus maiores voluptates facere. Officiis laborum consectetur deserunt!
    Repudiandae cupiditate id ullam vero, laboriosam qui cumque quae, illum recusandae rem officiis? Neque sunt earum laborum pariatur ipsam ex quia placeat ullam porro. Debitis illo eligendi repudiandae recusandae earum?
    Eveniet magnam voluptate atque, nemo sunt illum quae? Unde optio, necessitatibus ad minus magni velit iste perspiciatis. Quo velit aperiam, deleniti, quam facilis facere a exercitationem voluptatibus, et eos dolorum!
    Quos consequuntur, consequatur, voluptatum accusantium nisi quam quo similique cupiditate ut amet beatae fugiat consectetur vel nulla rerum voluptate ipsam. Quod eos unde porro beatae sequi a asperiores itaque doloremque?
    Ullam aspernatur optio libero corporis deserunt nisi atque ipsa quae debitis iste quod eaque obcaecati dolorum blanditiis explicabo similique ratione aperiam, reiciendis a odio quisquam tempora. Reiciendis soluta expedita nam.
    Animi quia minima minus eveniet recusandae ea vero provident, quam consectetur quaerat, aliquid sequi harum nemo mollitia sunt quasi beatae incidunt, placeat eum aut architecto. Esse magni quae quidem nobis.
    Blanditiis laudantium, quae iusto rerum similique quos tempore labore. Delectus consectetur cumque distinctio voluptas totam reprehenderit possimus quo nulla dolores similique minus aspernatur molestias mollitia rerum animi, in culpa non.
    Cupiditate vel quibusdam deserunt corrupti laboriosam itaque fuga laborum minus sapiente omnis magnam minima accusamus molestias facilis, consectetur libero dicta quidem perspiciatis. Voluptatibus accusantium doloremque cum aspernatur, adipisci quasi mollitia.
    Sunt, deserunt? Ullam commodi odio fugit impedit sapiente eligendi, iusto cumque omnis possimus atque adipisci, maxime consectetur ab recusandae officiis, sequi laboriosam eius corporis. Quo qui corrupti cumque temporibus impedit!
    Temporibus sed minus architecto iure vero aspernatur, incidunt eveniet tempora consequatur. Consectetur expedita asperiores, at quae doloremque aut officia debitis similique molestias dignissimos sint minima, quos fuga, quam eaque quis?
    Aspernatur vero fugiat vitae veniam deserunt molestias est magni adipisci placeat at inventore quia harum labore maxime, cupiditate praesentium fuga, id laboriosam quaerat? Veniam consequuntur, minima delectus nisi est tenetur.
    Asperiores repellendus possimus molestiae odio sequi culpa, eius id doloremque, repellat eum, nobis magni temporibus ut eos. Necessitatibus ducimus nesciunt, dicta sint qui consectetur voluptates unde neque suscipit veritatis recusandae!
    Aspernatur minus deleniti ea adipisci nulla tenetur ad, odit asperiores consequuntur dolorem consequatur cum, nesciunt saepe fugiat! Sint dolor aut, cupiditate reprehenderit, enim eius doloribus quasi saepe recusandae doloremque quae.
    Distinctio rerum temporibus possimus ut nostrum neque quaerat voluptatum aut at magni consequuntur aliquid mollitia, obcaecati quod adipisci numquam ratione tenetur illo magnam, fuga dignissimos laboriosam non nesciunt molestias. Placeat!
    Accusamus beatae rem aperiam reiciendis quibusdam error corporis perspiciatis repellendus fuga expedita maxime unde est tempore assumenda deserunt suscipit sed ipsam quaerat magni, nesciunt enim quisquam ea sint. Id, illo!
    Culpa perferendis asperiores quibusdam porro, deserunt libero id veniam earum. Repellendus eius consequatur id ipsam quo? Cumque magnam fugit corrupti? Quis nemo minus excepturi ipsum accusantium fugit doloremque, culpa optio?
    Laboriosam veritatis, minima temporibus mollitia asperiores dolores? Consectetur, pariatur. Ipsam ex consectetur vitae, sint eveniet dolores excepturi odio facere magnam, iusto officiis tempora eaque repellendus velit aliquam laboriosam, beatae est?
    Animi est ratione, culpa sint mollitia alias sapiente optio odit cum maiores vel aspernatur minima magni totam voluptates atque vitae, aliquid adipisci laborum a impedit delectus quaerat quia. Distinctio, repudiandae?
    Alias fuga aperiam modi nihil voluptate nulla fugiat corporis odit itaque quisquam minima assumenda consectetur, officia dicta, at aspernatur beatae quos. Ad incidunt, explicabo soluta voluptatem fugit ipsam beatae voluptatibus.
    Soluta ipsam iste nesciunt, quia ducimus ratione. Itaque odio voluptate doloremque est laborum mollitia eius. Illum sapiente accusantium porro voluptas numquam odio magni adipisci natus, illo, tempore totam id enim!
    Quibusdam, quod. Numquam eius temporibus amet aspernatur fugiat unde. Repellendus distinctio unde velit beatae. Aperiam dolorum harum placeat voluptatem saepe optio dolore at, explicabo reprehenderit dolor iste rem praesentium necessitatibus.
    Quaerat error incidunt, veniam optio ex velit recusandae repellat. Laboriosam cum corporis praesentium alias, quam, modi consequatur enim quas, odio harum ipsa. Nostrum at doloremque deleniti distinctio ad veniam iusto.
    Hic vitae odit, laborum omnis soluta nulla, impedit quisquam temporibus est animi tempore suscipit, asperiores commodi ullam. Aperiam, fugit ipsum repellendus dignissimos accusantium eligendi, sunt officiis quo culpa odit voluptates.
    Modi, quidem debitis? Tenetur, ipsum fuga. Expedita, reiciendis est quidem nostrum cumque quas itaque iste voluptatibus repellat. Iure reiciendis sed consectetur accusamus, aspernatur ex aut, fugiat saepe corporis pariatur a.
    Ipsam enim animi sequi quia odit deserunt, minus quisquam eveniet accusantium aut esse architecto provident, corrupti quam exercitationem maxime facilis impedit dignissimos optio voluptas fuga labore id. Eius, praesentium vel!
    Ipsum placeat assumenda facere excepturi ex corrupti distinctio, quisquam aperiam. Quis perspiciatis autem nostrum eaque in quo nobis repudiandae cumque ipsa at alias voluptatibus officia soluta, quisquam magnam? Exercitationem, nemo.
    Explicabo ut porro, vel fugiat voluptates alias molestiae nihil esse laudantium culpa necessitatibus cupiditate rerum, nostrum provident aperiam nemo soluta facere eveniet? Incidunt quisquam deserunt nisi eligendi quasi, nam numquam.
    Nulla aliquam delectus saepe maiores? Ipsa nostrum ipsam debitis totam itaque minus. Cupiditate neque tenetur ducimus alias. Quibusdam, voluptas odit, at, sit ad sequi molestiae explicabo ut quasi eius voluptatibus.
    Reprehenderit ullam unde aperiam expedita voluptas laboriosam tenetur velit, autem ratione in facere nesciunt aliquid molestiae sed consequatur ipsam temporibus libero. Nam fuga rerum earum dolore dolorem natus assumenda veritatis.
    Nemo consequatur illo corrupti vitae nam eum aspernatur ipsum. Deserunt error eveniet optio temporibus dolorem quas, atque, quis perspiciatis tenetur voluptatem ipsam quam dolore vero totam quibusdam harum odit repellendus.
    Labore provident necessitatibus nemo commodi, veniam, blanditiis quis ipsam libero ratione maiores possimus. Illo laborum, error asperiores aut totam dolor? Odit fugiat expedita maiores impedit magnam tenetur molestiae, ratione voluptatem.
    Aut dolore sint, eaque quasi autem, quo numquam perspiciatis ea aspernatur omnis assumenda, possimus sed sapiente non voluptatem labore deserunt vero. Tenetur adipisci illo ipsam tempora at soluta, distinctio aliquid.
    Totam numquam atque veritatis corrupti, sunt dolor corporis neque blanditiis excepturi quis fugiat dolores expedita quo, obcaecati ipsum dolorum nulla dolore voluptatem laboriosam distinctio ut nostrum! Sapiente corporis quas repellendus.
    Maiores quaerat quia iusto excepturi fuga et enim earum ut consequatur quo, molestias odio. Ad unde inventore illo reprehenderit perferendis assumenda aut ut officiis. Ipsam quam assumenda rem voluptatibus architecto.
    Ab perferendis similique aut illum debitis nemo velit, saepe vel adipisci voluptates aperiam eaque inventore sequi ducimus minus nam praesentium cum! Quisquam incidunt nesciunt nostrum voluptate ratione commodi. Cupiditate, maiores!
    Totam laborum pariatur, sit debitis adipisci quis dicta illo deserunt natus! Voluptate ducimus quas neque doloribus impedit quasi suscipit laudantium veritatis laboriosam nemo sint, voluptatem vel blanditiis, nam necessitatibus! Facere?
    Blanditiis deserunt laudantium vitae in facilis aliquam animi, voluptas accusantium ullam fuga, ipsum beatae ea iste ratione tempora doloribus alias, delectus iusto at eos. Ea ut deleniti fugit mollitia inventore!
    Amet molestiae architecto, alias nobis temporibus facilis a quam natus cum asperiores voluptatem facere impedit consectetur esse hic ea qui, sed minima, vitae nulla in ipsa? Adipisci vel corrupti itaque!
    Ab suscipit voluptatum quibusdam quae rerum, a autem totam ipsam necessitatibus voluptates veritatis cumque enim eum ducimus assumenda ullam repellat aspernatur quas. Commodi aliquam cupiditate, incidunt adipisci ut consequuntur consequatur.
    Repellat sequi debitis molestias quam minima alias, eum, autem aliquid obcaecati voluptates quibusdam laboriosam incidunt suscipit voluptas aliquam dolorem, libero cum nobis tenetur! Dolorem assumenda minima autem expedita incidunt cum?
    Illum, ea. Et explicabo quibusdam tenetur eius? Assumenda necessitatibus eos neque, cumque velit dolore, labore perspiciatis voluptatibus et quis similique dolorum eveniet voluptates sit voluptas illum temporibus eum nam delectus?
    Natus sapiente iste ullam perferendis accusamus modi qui delectus blanditiis totam nisi quibusdam similique voluptatum laborum reiciendis ducimus sunt alias fuga, quisquam optio necessitatibus dolor amet repellendus! Ex, amet incidunt.
    Incidunt suscipit obcaecati facilis nulla eius natus reiciendis? Quis eius accusamus accusantium? Atque ad soluta praesentium incidunt debitis, corrupti error dolorem cumque earum quidem ea rerum. Cumque dolorum cupiditate ea.
    Nulla non aliquid ea aut modi dignissimos? Ipsam, placeat nam voluptatum eos consequatur quas fuga reprehenderit? Similique molestiae, soluta optio quia distinctio eaque ipsum accusantium quos nam saepe blanditiis vel?
    Mollitia facere aspernatur consequatur dolor neque porro, odio earum eveniet autem perferendis deserunt, veniam magnam, aliquid expedita ex. Est ab quia nostrum. Aliquam iste eaque veritatis fuga eos odio facilis?
    Explicabo, dolorum delectus reprehenderit sunt mollitia quam placeat ab expedita in praesentium? Labore laborum impedit iste dolorem commodi accusantium voluptatem incidunt, velit tempora explicabo ex repellat dolores ipsum adipisci sed?
    Inventore, dolores delectus, ullam magni illum distinctio totam quisquam asperiores aspernatur quos quidem facere deserunt porro, sapiente eos obcaecati harum sequi alias! Voluptatum consequatur accusamus vitae iusto earum, consectetur voluptates?
    Quod provident, corporis eos iste quaerat fugit dolorem velit possimus reiciendis fuga ratione hic aliquam maiores sapiente illum quibusdam! Ratione iure eaque amet dicta repudiandae a quisquam aspernatur maiores hic.
    Unde atque, ratione minima nobis dolorum adipisci iusto tempora earum blanditiis eum. Expedita tempora minus placeat iusto? Accusantium quasi dignissimos magnam rerum nemo aliquid similique harum, repellat, incidunt ad perferendis!
    Facere suscipit expedita ea magni sunt beatae quos quo repellendus aut! Recusandae, consequatur. Odit, eveniet omnis sapiente aut deserunt voluptatibus, voluptatum distinctio neque illum dicta expedita, magnam repellendus possimus vitae?
    Officia voluptas in quod esse inventore voluptatibus recusandae nam vero minus? Vel, dolore numquam ab debitis quaerat eveniet tempore omnis molestiae placeat reprehenderit eos facere maiores blanditiis neque vero? Tenetur.
    Atque ipsum quisquam nesciunt eaque tempora consequuntur rerum nisi praesentium accusamus aperiam, pariatur quis eius quia aspernatur vitae dolorem temporibus repellat doloremque itaque totam ea at, impedit consequatur maiores? Commodi!
    Ipsam natus dolorem illo dicta consectetur nostrum quaerat odio ex inventore eum blanditiis deserunt possimus distinctio amet doloribus reiciendis pariatur provident iure explicabo similique recusandae, perferendis culpa fugit. Laudantium, aspernatur.
    Modi, sint maxime eveniet nobis laudantium vero cupiditate? Culpa repellat mollitia quos delectus possimus temporibus totam ducimus minima sunt adipisci expedita deleniti assumenda nisi, sint ullam dolores, commodi dolorem. Iure.
    Fuga autem itaque distinctio officia facere nam corrupti vero sit inventore dicta officiis, doloribus veniam aperiam consequuntur esse est adipisci quas! Itaque veniam aliquid neque reiciendis recusandae omnis reprehenderit! Ipsa.
    Cupiditate rerum cum velit perspiciatis molestiae quos doloremque atque non repellendus eaque aliquam quam quidem laboriosam sapiente consequuntur ratione ipsa, sunt illo iure explicabo? Cum blanditiis natus impedit eum dolore.
    Omnis aut nam accusantium. Minima quos perferendis fugit soluta, repellat explicabo aliquam perspiciatis id fuga similique aspernatur exercitationem suscipit ab cumque. Suscipit delectus accusantium, officia modi tempora ullam atque magni.
    Enim saepe iste vitae, beatae excepturi vero rem explicabo assumenda sunt animi fugiat exercitationem perspiciatis sint tempora hic nisi perferendis accusamus quas! Architecto eaque, tempore laudantium odio doloribus debitis modi!
    Illum dolores laborum vitae inventore et nisi cupiditate repellat odio eum iste, molestiae, ducimus amet, laudantium perspiciatis ratione laboriosam id aspernatur mollitia omnis distinctio consectetur rem. Libero temporibus et quam.
</body>
</html> 