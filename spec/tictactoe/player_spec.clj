(ns tictactoe.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]
			[tictactoe.game_rules :refer :all]
			[tictactoe.board :refer :all]))

(describe "Player"
	(def human-player (tictactoe.player.Human. "x"))
	(def easy-computer-player (tictactoe.player.EasyComputer. "x"))
	(def ultimate-computer-player (tictactoe.player.UltimateComputer. "x"))
	
	(defn contains-value? [value collection]
		(not (nil? (some #{value} collection))))
	
	(it "has a marker"
		(should= "x" (:marker human-player)))

	(context "Human Player"
		(context "get-move"
			(it "should return the user's move if it is valid"
				(let [board (make-board 9 nil)]
					(should= 1 (with-redefs [read-line (constantly "1") println (constantly "")] (get-move human-player board)))))
			(it "should prompt the user for another move if it is invalid" (pending "ability to test recursive input"))))
	
	(context "Easy Computer Player (random spots)"
		(context "get-move"
			(it "should return a move in an empty space"
				(let [board ["x" "x" "x" "x" "x" "x" "x" "x" nil]] (should= 8 (get-move easy-computer-player board))))))

	(context "Ulimate Computer Player (negamax algorithm)"		
		(context "get-move"
			(it "should return a move in an empty space"
				(should= 2 (get-move ultimate-computer-player ["x" "x" nil nil nil nil nil nil nil]))))))

(run-specs)