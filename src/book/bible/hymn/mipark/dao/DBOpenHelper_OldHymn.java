package book.bible.hymn.mipark.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper_OldHymn extends SQLiteOpenHelper {
	public static String [] title = {
			"1장 만복의 근원 하나님 ",
			"2장 성부 성자 성령께 ",
			"3장 이 천지간 만물들아 ",
			"4장 성부 성자 성령께 ",
			"5장 주 성부 성자 성령께 ",
			"6장 찬양 성부 성자 성령 ",
			"7장 구주와 왕이신 우리의 하나님 ",
			"8장 목소리 높여서 ",
			"9장 거룩 거룩 거룩 ",
			"10장 거룩하신 하나님 ",
			"11장 거룩한 주님께 ",
			"12장 고난받은 주를 보라 ",
			"13장 기뻐하며 경배하세 ",
			"14장 구세주를 아는 이들 ",
			"15장 내영혼 이제 깨어서 ",
			"16장 내 주는 살아 계시고 ",
			"17장 내가 한 맘으로 ",
			"18장 내 영혼아 곧 깨어 ",
			"19장 내 영혼아 찬양하라 ",
			"20장 다 감사 드리세 ",
			"21장 다 찬양하여라 ",
			"22장 다 함께 주를 경배하세 ",
			"23장 만 입이 내게 있으면 ",
			"24장 다 함께 주를 경배하세 ",
			"25장 면류관 가지고 ",
			"26장 만유의 주 앞에 ",
			"27장 빛나고 높은 보좌와 ",
			"28장 복의 근원 강림하사 ",
			"29장 성도여 다 함께 ",
			"30장 여호와 하나님 ",
			"31장 영광의 왕께 다 경배하며 ",
			"32장 오 하나님 우리의 창조주시니 ",
			"33장 온 천하 만물 우러러 ",
			"34장 전능왕 오셔서 ",
			"35장 속죄하신 구세주를 ",
			"36장 주 예수 이름 높이어 ",
			"37장 주 예수 이름 높이어 ",
			"38장 주의 영광 빛나니 ",
			"39장 주 은혜를 받으려 ",
			"40장 주 하나님 지으신 모든 세계 ",
			"41장 큰 영광중에 계신 주 ",
			"42장 찬란한 주의 영광은 ",
			"43장 찬송으로 보답할 수 없는 ",
			"44장 찬송하는 소리 있어 ",
			"45장 참 놀랍도다 주 크신 이름 ",
			"46장 찬양하라 복되신 구세주 예수 ",
			"47장 주여 우리 무리를 ",
			"48장 만유의 주재 ",
			"49장 참 즐거운 노래를 ",
			"50장 큰 영화로신 주 ",
			"51장 존귀와 영광 ",
			"52장 햇빛을 받는 곳마다 ",
			"53장 하늘에 가득찬 영광의 하나님 ",
			"54장 하나님이 친히 ",
			"55장 하나님의 크신 사랑 ",
			"56장 지난 이레 동안에 ",
			"57장 즐겁게 안식할 날 ",
			"58장 이 날은 주의 정하신 ",
			"59장 성전을 떠나 가기 전 ",
			"60장 우리의 주여 ",
			"61장 주여 복을 비옵나니 ",
			"62장 주 이름으로 모였던 ",
			"63장 서산으로 해질때 ",
			"64장 지난 밤에 나 고요히 ",
			"65장 생명의 빛 주 예수여 ",
			"66장 지난 밤에 보호하사 ",
			"67장 영혼의 햇빛 예수여 ",
			"68장 하나님 아버지 어둔 밤이 지나 ",
			"69장 나 가진 모든 것 ",
			"70장 모든 것이 주께로부터 ",
			"71장 내게 있는 모든 것을 ",
			"72장 하나님이 언약하신 ",
			"73장 내 눈을 들어 두루 살피니 ",
			"74장 오 만세 반석이신 ",
			"75장 저 높고 푸른 하늘과 ",
			"76장 저 해와 달과 별들이 ",
			"77장 전능의 하나님 ",
			"78장 참 아름다와라 ",
			"79장 피난처 있으니 ",
			"80장 주 하나님 크신 능력 ",
			"81장 귀하신 주의 이름은 ",
			"82장 나의 기쁨 나의 소망되시며 ",
			"83장 나의 맘에 수심 구름 ",
			"84장 나 어느날 꿈속을 헤매며 ",
			"85장 구주를 생각만 해도 ",
			"86장 내가 참 의지하는 예수 ",
			"87장 내 주님 입으신 그 옷은 ",
			"88장 내 진정 사모하는 ",
			"89장 샤론의 꽃 예수 ",
			"90장 성부의 어린양이 ",
			"91장 슬픈 마음 있는 사람 ",
			"92장 어둠의 권세에서 ",
			"93장 예수는 나의 힘이요 ",
			"94장 예수님은 누구신가 ",
			"95장 온 세상이 어두워 캄캄하나 ",
			"96장 온 세상이 캄캄하여서 ",
			"97장 위에 계신 나의 친구 ",
			"98장 주 예수 내가 알기전 ",
			"99장 주 예수 내 죄 속하니 ",
			"100장 죄인 괴수 날 위해 ",
			"101장 천지에 있는 이름 중 ",
			"102장 주 예수보다 더 귀한것은 없네 ",
			"103장 참 목자 우리 주 ",
			"104장 곧 오소서 임마누엘 ",
			"105장 오랫동안 기다리던 ",
			"106장 이새의 뿌리에서 ",
			"107장 영원한 문아 열려라 ",
			"108장 구주 탄생하심을 ",
			"109장 고요한밤 거룩한밤 ",
			"110장 공중에는 노래 ",
			"111장 귀중한 보배합을 ",
			"112장 그 맑고 환한 밤중에 ",
			"113장 그 어린 주 예수 ",
			"114장 그 어린 주 예수 ",
			"115장 기쁘다 구주 오셨네 ",
			"116장 동방박사 세 사람 ",
			"117장 만백성 기뻐하여라 ",
			"118장 영광 나라 천사들아 ",
			"119장 옛날 임금 다윗성에 ",
			"120장 오 베들레헴 작은 골 ",
			"121장 우리 구주 나신 날 ",
			"122장 참 반가운 신도여 ",
			"123장 저 들밖에 한 밤중에 ",
			"124장 한 밤에 양을 치는자 ",
			"125장 천사들의 노래가 ",
			"126장 천사 찬송하기를 ",
			"127장 예수님의 귀한 사랑 ",
			"128장 오 영원한 내 주 예수 ",
			"129장 오 젊고 용감하신 ",
			"130장 왕되신 우리 주께 ",
			"131장 주 예수 나귀 타고 ",
			"132장 호산나 호산나 ",
			"133장 어저께나 오늘이나 ",
			"134장 감람산 깊은 밤중에 ",
			"135장 갈보리산 위에 ",
			"136장 거기 너 있었는가 ",
			"137장 놀랍다 주님의 큰 은혜 ",
			"138장 만왕의 왕 내 주께서 ",
			"139장 생명의 주여 면류관 ",
			"140장 성도들아 다 나아와 ",
			"141장 웬말인가 날 위하여 ",
			"142장 영화로신 주 예수의 ",
			"143장 십자가에 달리신 ",
			"144장 예수 나를 위하여 ",
			"145장 오 거룩하신 주님 ",
			"146장 저 멀리 푸른 언덕에 ",
			"147장 주 달려 죽은 십자가 ",
			"148장 주가 지신 십자가를 ",
			"149장 기뻐 찬송하세 ",
			"150장 무덤에 머물러 ",
			"151장 다시 사신 구세주 ",
			"152장 사망을 이긴 주 ",
			"153장 오늘 다시 사심을 ",
			"154장 예수 부활했으니 ",
			"155장 주님께 영광 ",
			"156장 싸움은 모두 끝나고 ",
			"157장 즐겁도다 이 날 ",
			"158장 하늘에 찬송이 들리던 그날 ",
			"159장 할렐루야 우리 예수 ",
			"160장 할렐루야 할렐루야 ",
			"161장 대속하신 구주께서 ",
			"162장 신랑되신 예수께서 ",
			"163장 언제 주님 다시 오실는지 ",
			"164장 오랫동안 고대하던 ",
			"165장 저 산너머 먼동튼다 ",
			"166장 주 예수 믿는 자여 ",
			"167장 주 예수의 강림이 ",
			"168장 하나님의 나팔소리 ",
			"169장 강물같이 흐르는 기쁨 ",
			"170장 구주여 크신 인애를 ",
			"171장 비둘기 같이 온유한 ",
			"172장 빈 들에 마른 풀 같이 ",
			"173장 불길 같은 성신여 ",
			"174장 성령의 은사를 ",
			"175장 성령이여 우리 찬송 부를때 ",
			"176장 영화로신 주성령 ",
			"177장 성령이여 강림하사 ",
			"178장 은혜가 풍성한 하나님은 ",
			"179장 이 기쁜 소식을 ",
			"180장 무한하신 주 성령 ",
			"181장 진실하신 주 성령 ",
			"182장 구주의 십자가 보혈로 ",
			"183장 나 속죄함을 받은 후 ",
			"184장 나의 죄를 씻기는 ",
			"185장 내 너를 위하여 ",
			"186장 내 주의 보혈은 ",
			"187장 너희 죄 흉악하나 ",
			"188장 만세반석 열리니 ",
			"189장 마음에 가득한 의심을 깨치고 ",
			"190장 샘물과 같은 보혈은 ",
			"191장 양 아흔 아홉 마리는 ",
			"192장 영원히 죽게될 내 영혼 ",
			"193장 예수 십자가에 흘린 피로써 ",
			"194장 우리를 죄에서 구하시려 ",
			"195장 이 세상의 모든 죄를 ",
			"196장 날 구원하신 예수를 ",
			"197장 이 세상 험하고 ",
			"198장 정결하게 하는 샘이 ",
			"199장 주 십자가를 지심으로 ",
			"200장 주의 피로 이룬 샘물 ",
			"201장 주의 확실한 약속의 말씀 듣고 ",
			"202장 죄에서 자유를 얻게 함은 ",
			"203장 나 행한 것으로 ",
			"204장 예수로 나의 구주 삼고 ",
			"205장 예수 앞에 나오면 ",
			"206장 오랫동안 모든 죄 가운데 빠져 ",
			"207장 주 나에게 주시는 ",
			"208장 주 예수 내 맘에 들어와 ",
			"209장 주의 말씀 받은 그날 ",
			"210장 내 죄 사함 받고서 ",
			"211장 그 참혹한 십자가에 ",
			"212장 너 성결키 위해 ",
			"213장 먹보다도 더 검은 ",
			"214장 변챦는 주님의 사랑과 ",
			"215장 이 죄인을 완전케 하옵시고 ",
			"216장 아버지여 나의 맘을 ",
			"217장 주님의 뜻을 이루소서 ",
			"218장 주 예수님 내 맘에 오사 ",
			"219장 주의 음성을 내가 들으니 ",
			"220장 구주 예수 그리스도 ",
			"221장 나 가난복지 귀한 성에 ",
			"222장 보아라 즐거운 우리 집 ",
			"223장 세상 모든 수고 끝나 ",
			"224장 저 요단강 건너편에 ",
			"225장 새 예루살렘 복된 집 ",
			"226장 저 건너편 강 언덕에 ",
			"227장 저 하늘 나라는 ",
			"228장 저 좋은 낙원 이르니 ",
			"229장 주 예수 다스리시는 ",
			"230장 저 뵈는 본향집 ",
			"231장 주가 맡긴 모든 역사 ",
			"232장 아름다운 본향 ",
			"233장 황무지가 장미꽃 같이 ",
			"234장 나의 사랑하는 책 ",
			"235장 달고 오묘한 그 말씀 ",
			"236장 주 예수 크신 사랑 ",
			"237장 저 높고 넓은 하늘이 ",
			"238장 주님의 귀한 말씀은 ",
			"239장 사랑의 하늘 아버지 ",
			"240장 참 사람되신 말씀 ",
			"241장 하나님 아버지 주신 책은 ",
			"242장 교회의 참된 터는 ",
			"243장 귀하신 주님 계신곳 ",
			"244장 천지 주관 하는 주님 ",
			"245장 시온성과 같은 교회 ",
			"246장 내 주의 나라와 ",
			"247장 이 세상 풍파 심하고 ",
			"248장 시온의 영광이 빛나는 아침 ",
			"249장 주 사랑하는 자 다 찬송할때에 ",
			"250장 아름다운 시온성아 ",
			"251장 구주께서 부르되 ",
			"252장 기쁜 소리 들리니 ",
			"253장 구원으로 인도하는 ",
			"254장 주 은총 입은 종들이 ",
			"255장 너 시온아 이 소식 전파하라 ",
			"256장 눈을 들어 하늘 보라 ",
			"257장 듣는 사람마다 복음 전하여 ",
			"258장 물건너 생명줄 던지어라 ",
			"259장 빛의 사자들이여 ",
			"260장 새벽부터 우리 ",
			"261장 어둔밤 마음에 잠겨 ",
			"262장 어둔 죄악길에서 ",
			"263장 예수 말씀하시기를 ",
			"264장 예수의 전한 복음 ",
			"265장 옳은 길 따르라 의의 길을 ",
			"266장 왕의 명령 전달할 사자여 ",
			"267장 주 날 불러 이르소서 ",
			"268장 온 세상 위하여 ",
			"269장 웬 일인가 내 형제여 ",
			"270장 우리가 지금은 나그네 되어도 ",
			"271장 익은 곡식 거둘 자가 ",
			"272장 인류는 하나되게 ",
			"273장 저 북방 얼음산과 ",
			"274장 주 예수 넓은사랑 ",
			"275장 저 죽어가는 자 다 구원하고 ",
			"276장 하나님의 진리 등대 ",
			"277장 흑암에 사는 백성들을 보라 ",
			"278장 사랑하는 주님 앞에 ",
			"279장 주 하나님의 사랑은 ",
			"280장 생전에 우리가 ",
			"281장 아무 흠도 없고 ",
			"282장 유월절 때가 이르매 ",
			"283장 주 앞에 성찬 받기 위하여 ",
			"284장 주 예수 해변서 ",
			"285장 오 나의 주님 친히 뵈오니 ",
			"286장 성부님께 빕니다 ",
			"287장 오늘 모여 찬송함은 ",
			"288장 완전한 사랑 ",
			"289장 고생과 수고가 다 지난 후 ",
			"290장 괴로운 인생길 가는 몸이 ",
			"291장 날빛보다 더 밝은 천국 ",
			"292장 내 본향 가는 길 ",
			"293장 천국에서 만나보자 ",
			"294장 친애한 이 죽으니 ",
			"295장 후일에 생명 그칠 때 ",
			"296장 오늘까지 복과 은혜 ",
			"297장 종소리 크게 울려라 ",
			"298장 실로암 샘물가에 핀 ",
			"299장 예수께서 오실 때에 ",
			"300장 예수께로 가면 ",
			"301장 사랑의 하나님 ",
			"302장 주님께 귀한 것 드려 ",
			"303장 가슴마다 파도친다 ",
			"304장 어머니의 넓은 사랑 ",
			"305장 사철에 봄바람 불어 잇고 ",
			"306장 감사하는 성도여 ",
			"307장 공중 나는 새를 보라 ",
			"308장 넓은 들에 익은 곡식 ",
			"309장 논밭에 오곡백과 ",
			"310장 저 밭에 농부 나가 ",
			"311장 산마다 불이 탄다 ",
			"312장 묘한 세상 주시고 ",
			"313장 갈 길을 밝히 보이시니 ",
			"314장 기쁜 일이 있어 천국 종 치네 ",
			"315장 돌아와 돌아와 ",
			"316장 목마른 자들아 ",
			"317장 어서 돌아오오 ",
			"318장 예수가 우리를 부르는 소리 ",
			"319장 온유한 주님의 음성 ",
			"320장 주께서 문에 오셔서 ",
			"321장 자비한 주께서 부르시네 ",
			"322장 주께로 나오라 ",
			"323장 주께로 한 걸음씩 ",
			"324장 주님 찾아 오셨네 ",
			"325장 주 예수 대문 밖에 ",
			"326장 죄짐에 눌린 사람은 ",
			"327장 죄짐을 지고서 곤하거든 ",
			"328장 천성길을 버리고 ",
			"329장 형제여 지체말라 ",
			"330장 고통의 멍에 벗으려고 ",
			"331장 나 주를 멀리 떠났다 ",
			"332장 나 행한것 죄 뿐이니 ",
			"333장 날마다 주와 버성겨 ",
			"334장 아버지여 이 죄인을 ",
			"335장 양떼를 떠나서 ",
			"336장 여러 해 동안 주 떠나 ",
			"337장 인애하신 구세주여 ",
			"338장 천부여 의지 없어서 ",
			"339장 큰 죄에 빠진 날 위해 ",
			"340장 구주 예수 의지함이 ",
			"341장 너 하나님께 이끌리어 ",
			"342장 어려운 일 당할때 ",
			"343장 울어도 못하네 ",
			"344장 이 눈에 아무 증거 아니 뵈어도 ",
			"345장 주 하나님 늘 믿는 자 ",
			"346장 값비싼 향유를 주께 드린 ",
			"347장 겸손히 주를 섬길때 ",
			"348장 나의 생명드리니 ",
			"349장 나 주의 도움 받고자 ",
			"350장 나의 죄를 정케하사 ",
			"351장 날 대속하신 예수께 ",
			"352장 내 임금 예수 내 주여 ",
			"353장 내 주 예수 주신 은혜 ",
			"354장 내 죄 속해 주신 주께 ",
			"355장 부름받아 나선 이 몸 ",
			"356장 성자의 귀한 몸 ",
			"357장 세상의 헛된 신을 버리고 ",
			"358장 아침 해가 돋을 때 ",
			"359장 예수가 함께 계시니 ",
			"360장 예수 나를 오라 하네 ",
			"361장 주의 주실 화평 ",
			"362장 하나님이 말씀하시기를 ",
			"363장 내 모든 시험 무거운 짐을 ",
			"364장 내 주를 가까이 하게 함은 ",
			"365장 내 주의 지신 십자가 ",
			"366장 어지러운 세상 중에 ",
			"367장 십자가를 내가 지고 ",
			"368장 내 죄를 회개하고 ",
			"369장 네 맘과 정성을 다하여서 ",
			"370장 어둔밤 쉬 되리니 ",
			"371장 삼천리 반도 금수강산 ",
			"372장 나 맡은 본분은 ",
			"373장 세상 모두 사랑 없어 ",
			"374장 너 주의 사람아 ",
			"375장 영광을 받으신 만유의 주여 ",
			"376장 내 평생 소원 이것 뿐 ",
			"377장 예수 따라가며 ",
			"378장 이전에 주님을 내가 몰라 ",
			"379장 주의 말씀 듣고서 ",
			"380장 내 마음 주께 드리니 ",
			"381장 충성하라 죽도록 ",
			"382장 허락하신 새 땅에 ",
			"383장 환난과 핍박 중에도 ",
			"384장 내 주는 강한 성이요 ",
			"385장 군기를 손에 높이 들고 ",
			"386장 힘차게 일어나 ",
			"387장 나는 예수 따라가는 ",
			"388장 마귀들과 싸울지라 ",
			"389장 믿는 사람들은 군병같으니 ",
			"390장 십자가 군병들아 ",
			"391장 십자가 군병되어서 ",
			"392장 예수의 이름 힘 입어서 ",
			"393장 우리들의 싸울 것은 ",
			"394장 주를 앙모하는 자 ",
			"395장 너 시험을 당해 ",
			"396장 주 예수 이름 소리 높여 ",
			"397장 주 믿는 사람 일어나 ",
			"398장 주 예수 우리 구하려 ",
			"399장 주의 약속하신 말씀 위에 서 ",
			"400장 주의 진리 위해 십자가 군기 ",
			"401장 천성을 향해 가는 성도들아 ",
			"402장 행군 나팔 소리로 ",
			"403장 나 위하여 십자가의 ",
			"404장 그 크신 하나님의 사랑 ",
			"405장 나같은 죄인 살리신 ",
			"406장 내 맘이 낙심되며 ",
			"407장 그 영원하신 사랑은 ",
			"408장 내 주 하나님 넓고 큰 은혜는 ",
			"409장 목마른 내 영혼 ",
			"410장 아 하나님의 은혜로 ",
			"411장 예수 사랑하심은 ",
			"412장 우리는 주님을 늘 배반하나 ",
			"413장 외롭게 사는 이 그 누군가 ",
			"414장 주의 사랑 비췰때에 ",
			"415장 주 없이 살 수 없네 ",
			"416장 하나님은 외아들을 ",
			"417장 큰 죄에 빠진 나를 ",
			"418장 하나님 사랑은 ",
			"419장 구주여 광풍이 일어 ",
			"420장 그 누가 나의 괴롬 알며 ",
			"421장 나는 갈길 모르니 ",
			"422장 나그네와 같은 내가 ",
			"423장 나의 믿음 약할 때 ",
			"424장 나의 생명 되신 주 ",
			"425장 나 캄캄한 밤 죄의 길에 ",
			"426장 날 위하여 날 위하여 ",
			"427장 내가 매일 기쁘게 ",
			"428장 내가 환난 당할 때에 ",
			"429장 내 갈 길 멀고 밤은 깊은데 ",
			"430장 내 선한 목자 ",
			"431장 내 주여 뜻대로 ",
			"432장 너 근심걱정 말아라 ",
			"433장 눈을 들어 산을 보니 ",
			"434장 나의 갈길 다 가도록 ",
			"435장 못 박혀 죽으신 ",
			"436장 다정하신 목자 예수 ",
			"437장 주 나의 목자 되시니 ",
			"438장 예부터 도움 되시고 ",
			"439장 만세반석 열린 곳에 ",
			"440장 멀리 멀리 갔더니 ",
			"441장 비바람이 칠 때와 ",
			"442장 선한 목자 되신 우리 주 ",
			"443장 시험 받을 때에 ",
			"444장 예수가 거느리시니 ",
			"445장 오 나의 하나님 ",
			"446장 오 놀라운 구세주 ",
			"447장 오 신실하신 주 ",
			"448장 이 세상 끝날까지 ",
			"449장 이 세상의 친구들 ",
			"450장 자비하신 예수여 ",
			"451장 전능하신 여호와여 ",
			"452장 주는 귀한 보배 ",
			"453장 주는 나를 기르시는 목자 ",
			"454장 주 사랑안에 살면 ",
			"455장 주 안에 있는 나에게 ",
			"456장 주와 같이 길가는 것 ",
			"457장 주의 곁에 있을 때 ",
			"458장 주의 친절한 팔에 안기세 ",
			"459장 지금까지 지내 온 것 ",
			"460장 지금까지 지내 온 것 ",
			"461장 캄캄한 밤 사나운 바람불 때 ",
			"462장 큰 물결이 설레는 어둔 바다 ",
			"463장 험한 시험 물속에서 ",
			"464장 곤한 내 영혼 편히 쉴 곳과 ",
			"465장 구주와 함께 나 죽었으니 ",
			"466장 나 어느 곳에 있든지 ",
			"467장 내게로 와서 쉬어라 ",
			"468장 내 맘에 한 노래 있어 ",
			"469장 내 영혼의 그윽히 깊은데서 ",
			"470장 내 평생에 가는 길 ",
			"471장 십자가 그늘 밑에 ",
			"472장 영광스럽도다 참된 평화는 ",
			"473장 아 내 맘속에 ",
			"474장 이 세상에 근심된 일이 많고 ",
			"475장 이 세상은 요란하나 ",
			"476장 주 예수 넓은 품에 ",
			"477장 바다에 놀이 치는 때 ",
			"478장 주 날개 밑 내가 편안히 쉬네 ",
			"479장 내가 깊은 곳에서 ",
			"480장 기도하는 이시간 ",
			"481장 주여 복을 주시기를 ",
			"482장 내 기도하는 그 시간 ",
			"483장 너 예수께 조용히 나가 ",
			"484장 마음 속에 근심있는 사람 ",
			"485장 어두운 내 눈 밝히사 ",
			"486장 주 예수여 은혜를 ",
			"487장 죄짐 맡은 우리 구주 ",
			"488장 내 영혼에 햇빛 비치니 ",
			"489장 세상 모든 풍파 너를 흔들어 ",
			"490장 귀하신 주여 날 붙드사 ",
			"491장 귀하신 친구 내게 계시니 ",
			"492장 나의 영원하신 기업 ",
			"493장 나 이제 주님의 새 생명 얻은몸 ",
			"494장 나 죄중에 헤매며 ",
			"495장 내 영혼이 은총 입어 ",
			"496장 십자가로 가까이 ",
			"497장 어디든지 예수 나를 이끌면 ",
			"498장 은혜구한 내게 은혜의 주님 ",
			"499장 저 장미꽃 위에 이슬 ",
			"500장 주 음성 외에는 ",
			"501장 주의 십자가 있는 데 ",
			"502장 태산을 넘어 험곡에 가도 ",
			"503장 고요한 바다로 ",
			"504장 예수 영광 버리사 ",
			"505장 내 모든 소원 기도의 제목 ",
			"506장 예수 더 알기 원함은 ",
			"507장 주님의 마음을 본받는 자 ",
			"508장 주와 같이 되기를 ",
			"509장 거친 세상에서 실패하거든 ",
			"510장 겟세마네 동산의 ",
			"511장 내 구주 예수를 더욱 사랑 ",
			"512장 내 주되신 주를 참 사랑하고 ",
			"513장 너희 마음에 슬픔이 가득 차도 ",
			"514장 누가 주를 따라 ",
			"515장 뜻없이 무릎 꿇는 ",
			"516장 맘 가난한 사람 ",
			"517장 생명 진리 은혜되신 ",
			"518장 신자되기 원합니다 ",
			"519장 십자가를 질 수 있나 ",
			"520장 주의 귀한 말씀을 ",
			"521장 어느 민족 누구게나 ",
			"522장 주님이 가신 섬김의 길은 ",
			"523장 나 형제를 늘 위해 ",
			"524장 우리 다시 만날 때까지 ",
			"525장 주 믿는 형제들 ",
			"526장 주 예수 안에 동서나 ",
			"527장 큰 은혜로 묶어주신 ",
			"528장 주여 나의 병든 몸을 ",
			"529장 큰 무리 주를 에워싼 중에 ",
			"530장 네 병든 손 내밀라고 ",
			"531장 때 저물어 날 이미 어두니 ",
			"532장 구름 같은 이 세상 ",
			"533장 내맘의 주여 소망되소서 ",
			"534장 세월이 흘러 가는데 ",
			"535장 어두운 후에 빛이 오며 ",
			"536장 이 곤한 인생이 ",
			"537장 엄동설한 지나가면 ",
			"538장 예루살렘 금성아 ",
			"539장 이 몸의 소망 무엔가 ",
			"540장 이세상 지나가고 ",
			"541장 저 요단강 건너편에 찬란하게 ",
			"542장 주여 지난 밤 내 꿈에 ",
			"543장 저 높은 곳을 향하여 ",
			"544장 잠시 세상에 내가 살면서 ",
			"545장 하늘 가는 밝은 길이 ",
			"546장 주 성전 안에 계시도다 ",
			"547장 진리와 생명 되신 주 ",
			"548장 주 기도문 영창 ",
			"549장 우리 기도를 ",
			"550장 주 너를 지키시고 ",
			"551장 아멘 ",
			"552장 두번 아멘 ",
			"553장 두번 아멘 ",
			"554장 두번 아멘 ",
			"555장 세번 아멘 ",
			"556장 세번 아멘 ",
			"557장 네번 아멘 ",
			"558장 일곱번 아멘 "
	};

    public DBOpenHelper_OldHymn(Context context) {
		super(context, "tongil_hymn.db", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
			String createtable = "create table tongil_hymn( _id integer primary key autoincrement,"+
			"title text, description integer);";
			db.execSQL(createtable);
			for(int i = 0; i < 558; i++){
				db.execSQL("insert into tongil_hymn values (null,'"+title[i]+"', 1)");
			}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exits tongil_hymn");
		onCreate(db);
	}
}