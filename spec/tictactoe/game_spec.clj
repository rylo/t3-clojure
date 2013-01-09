(ns tictactoe.core-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
			[tictactoe.board :refer :all]))

(describe "Game"
	(context "game-over?"
		(let [full-board (make-board 9 "x") empty-board (make-board 9 nil) row-vector (generate-rows)]
			(it "should return false if the game isn't over" (should= false (game-over? taken-row-present? empty-board row-vector)))
			(it "should return false if the game isn't over" (should= true (game-over? taken-row-present? full-board row-vector))))))

(run-specs)