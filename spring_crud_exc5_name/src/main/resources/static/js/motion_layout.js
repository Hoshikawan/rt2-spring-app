// HTMLにあるid="regist_link"の要素を取得する
const regist_link = document.getElementById('regist_link');

// 要素が存在するか確認 ページによっては存在しない場合があるため、エラー防止のためのif文
if (regist_link){
	// マウスがリンクの上に乗った時の処理  mouseenter = ホバーした瞬間
	regist_link.addEventListener("mouseenter", () => {
		// aタグに「hover」というクラスを追加する→CSSのa.hoverが有効になり、色が変わる
		regist_link.classList.add("hover");
		regist_link.style.color = "#0bd";
	});

	// マウスがリンクから離れたときの処理  mouseleave = ホバー解除
	regist_link.addEventListener("mouseleave", () => {
		// 追加したhoverクラスを削除する→元の文字色に戻る
		regist_link.classList.remove("hover");
		regist_link.style.color = "";
	});
}

//const regist_link = document.getElementById('regist_link');
//const regist = regist_link.innerHTML;
//
////if (regist_link){
//	regist.addEventListener("mouseenter", () => {
//		regist.classList.add("hover");
//	});
//
//	regist.addEventListener("mouseleave", () => {
//		regist.classList.remove("hover");
//	});
////}
