(ns clojure-time.day3.day3_1
  (:require [clojure.string :as string]
            [clojure.test :refer :all]
            [clojure.pprint :refer :all]))

(defn parse [word] (zipmap [:left :top :width :height]
                           (rest (map read-string (re-seq #"\d+" word)))))
(def test-input "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2")

(defn prepare-input-data [input-string]
  (let [as-string-list (string/split-lines input-string)]
    (into [] (map parse as-string-list))))

(defn generate-2d-array [maps-list]
  (let
    [field-width (reduce max (map #(reduce + (vals (select-keys % [:left :width]))) maps-list))
     field-height (reduce max (map #(reduce + (vals (select-keys % [:top :height]))) maps-list))]
    (make-array Integer/TYPE field-height field-width)))

;(pprint  (prepare-input-data test-input))
;(def arr2d (generate-2d-array (prepare-input-data test-input)))
(defn pprint-2d-array [ar]
  (println (map #(with-out-str (pprint %)) ar)))


;(System/exit 0)
(defn update-field [arr2d {:keys [left top width height]}]
  (let [fh (count arr2d)
        fw (count (first arr2d))
        x-range (range left (+ left width))
        y-range (range top (+ top height))
        indices-of-rect (for [x x-range y y-range] [x y])]
    (doseq [x x-range y y-range]
      (let [
            row (aget arr2d y)
            cell-value (aget row x)]
        (aset row x (inc cell-value))))))





(defn get-result [input-list]
  (let [input-maps  (prepare-input-data input-list)
        field (generate-2d-array input-maps)]
    (pprint input-maps)
    (pprint input-maps)
    (pprint-2d-array field)
    (map pprint (list input-maps))

    (pprint-2d-array field)))

(get-result test-input)
;
;(deftest a-test
;  (testing (is (= 4 (get-result test-input)))))
;(a-test)

(let [input-data (prepare-input-data (slurp "input"))])


