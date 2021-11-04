(ns blackjack-v2.game
  (:require [card-ascii-art.core :as card]))

(defn new-card
  []
  "Generates a card number between 1 and 13."
  (inc (rand-int 13)))

(defn JQK->10
  [card]
  (if (> card 10) 10 card))

(defn A->11
  [card]
  (if (= card 1) 11 card))

(defn points-cards [cards]
  (let [card-without-JQK (map JQK->10 cards)
        card-with-A11   (map A->11 card-without-JQK)
        points-with-A-11  (reduce + card-with-A11)
        points-with-A1    (reduce + card-without-JQK)]
   (if (> points-with-A-11 21) points-with-A1 points-with-A-11)
    )
  )


(defn player
  [player-name]
    (let [card1 (new-card)
        card2 (new-card)
        cards [card1 card2]
        points (points-cards cards)]
    {
     :player-name player-name
     :cards       cards
     :points      points
     }
    )
  )

(defn more-card
  [player]
  (let [
        card (new-card)
        cards (conj (:cards player) card)
        new-player (assoc player :cards cards)
        points (points-cards cards)]
    (assoc new-player :points points))

)
(def player (player "Osvaldo"))

(card/print-player (more-card player))
;(card/print-player (player "Mateus"))
