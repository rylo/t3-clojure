(ns tictactoe.core-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
			[tictactoe.board :refer :all]))

(describe "Player"
	(def player (tictactoe.player.Human. "x"))
	
	(it "has a marker"
		(should= "x" (:marker player)))

	(context "get-move"
		(it "should return the user's move if it is valid"
			(let [board (make-board 9 nil)]
				(should= 1 (with-redefs [read-line (constantly "1") println (constantly "")] (get-move player board)))))
		(it "should prompt the user for another move if it is invalid" (pending "ability to test recursive input")))
)

(run-specs)