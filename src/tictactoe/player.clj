(ns tictactoe.player)

(defprotocol CanPlay
	(marker [this]))

(defrecord Player [marker]
	CanPlay
	(marker [this] (:marker this)))