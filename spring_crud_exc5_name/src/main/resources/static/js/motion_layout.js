// HTMLにあるid="regist_link"の要素を取得する
const regist_link = document.getElementById('regist_link');

// 要素が存在するか確認  Javaでいうif(regist_link != null)とほぼ同じ意味
// HTMLからregist_linkが消えたときに、nullに対してメソッドを呼ぼうとしているからエラーになってしまう
// エラーが出ないようにするために、「もしregist_linkがあったら」とif文を記述している
if (regist_link){
	// イベントが起きたら何をするか をそのままコードにしたのが「addEventListener」
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

// HTMLの中からclass="color_box"を持つ要素を全て取得する   idじゃない理由は、設定したい条件が複数あるから
// 引数の「.」を記述し忘れるとエラーは出ないけど動きもしない。
const boxes = document.querySelectorAll('.color_box_js');
if (boxes){
	// boxesを1つずつ順番に処理するためにforEachメソッドを使う  (を一つ忘れてずっとエラー出てたので注意
	boxes.forEach((box) => {
		// 現在処理しているdivの中にあるinput要素を１つずつ取得するためのコード
		// →複数の要素を一つずつ取り出して、同じ処理を繰り返すための記述
		const input = box.querySelector("input");
	
		box.addEventListener("mouseenter", () => {
			const red = Math.floor(Math.random() * 256);
			const green = Math.floor(Math.random() * 256);
			const blue = Math.floor(Math.random() * 256);
			// 'と`は別物
			input.style.borderColor = `rgb(${red}, ${green}, ${blue})`;
		});
	
		box.addEventListener("mouseleave", () => {
			input.style.borderColor = "";
		});
	});
	
}
