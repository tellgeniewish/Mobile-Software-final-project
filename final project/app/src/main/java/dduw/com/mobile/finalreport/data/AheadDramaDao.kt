package dduw.com.mobile.finalreport.data

import dduw.com.mobile.finalreport.R

class AheadDramaDao {
    private val dramas = ArrayList<DramaDto>()
//    init {
//        dramas.add(DramaDto(0, R.mipmap.mydearest,"연인", "MBC", "남궁민", "안은진", "김성용", "황진영", "병자호란과 함께 역사 속에서 성숙해지는 두 주인공 장현길채는 완전 짱이었다, 사랑에 압도된 이가 어디까지 할 수 있는지 보여줌, 본방으로 봤다... 매주 금토마다 활력이 생기게 했던 드라마, 난 14화가 젤루 좋아 ㅋ"))
//        dramas.add(DramaDto(1, R.mipmap.secretgarden,"시크릿 가든", "SBS", "현빈", "하지원", "신우철", "김은숙","김은숙 드라마 중에 젤 재밌다ㅎㅎ 판타지 최고! 이태리 장인이 한땀한땀~~~"))
//        dramas.add(DramaDto(2, R.mipmap.theworldthattheylivein,"그들이 사는 세상", "KBS", "현빈", "송혜교", "표민수", "노희경", "노동자들의 다양한 인간관계 이야기.. 나레이션이 참 좋다, 완전한 선/악 구별이 없어서 현실적이다"))
//        dramas.add(DramaDto(3, R.mipmap.discoveryoflove,"연애의 발견", "KBS", "문정혁", "정유미", "김성윤", "정현정", "어떻게 보면 비현실적인데 동시에 엄청 현실적.. 그리고 너무 공감되는 장면이 많다"))
//        dramas.add(DramaDto(4, R.mipmap.queserasera,"케세라세라", "MBC", "문정혁", "정유미", "김윤철", "도현정", "에릭-정유미 첫 드라마! ost랑 분위기가 좋고 정통 멜로 오랜만에 봐서 좋았다! 이거 보고 에릭 정유미 조합에 빠져서 연애의 발견도 봄"))
//    }

    fun getAllDramas() : ArrayList<DramaDto> {
        return dramas
    }

    fun addNewDrama(newDramaDto : DramaDto) {
        dramas.add(newDramaDto)
    }

    fun modifyDrama(pos: Int, modifyDramaDto : DramaDto) {
        dramas.set(pos, modifyDramaDto)
    }

    fun removeDrama(removeDramaDto : DramaDto) {
        val index = dramas.indexOf(removeDramaDto)
        dramas.removeAt(index)
    }

    fun getDramaByPos(pos : Int) = dramas.get(pos)
}