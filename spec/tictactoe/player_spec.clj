(ns tictactoe.core-spec
  (:require [speclj.core :refer :all]
            [tictactoe.player :refer :all]))

(describe "Player"
	(it "has a marker"
		(let [player (tictactoe.player.Player. "x")]
  		 	(should= "x" (:marker player)))))

(run-specs)