// HTMLにあるid="regist_link"の要素を取得する
const regist_link = document.getElementById('regist_link');

// 要素が存在するか確認  Javaでいうif(regist_link != null)とほぼ同じ意味
// HTMLからregist_linkが消えたときに、nullに対してメソッドを呼ぼうとしているからエラーになってしまう
// エラーが出ないようにするために、「もしregist_linkがあったら」とif文を記述している
if (regist_link){
	// マウスがリンクの上に乗った時の処理  mouseenter = ホバーした瞬間（カーソルが要素の上に乗った瞬間）
	regist_link.addEventListener("mouseenter", () => {
//		 ↓CSSで色を決めていたらこの記述が必要だったが、今回はJSで色指定の記述をしたので必要なくなった
//		regist_link.classList.add("hover");
		regist_link.style.color = "#0bd";
	});

	// マウスがリンクから離れたときの処理  mouseleave = ホバー解除（カーソルが要素から離れた瞬間）
	regist_link.addEventListener("mouseleave", () => {
		// 追加したhoverクラスを削除する→元の文字色に戻る
//		regist_link.classList.remove("hover");
		regist_link.style.color = "";
	});
}

