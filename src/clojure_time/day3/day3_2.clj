(ns clojure-time.day3.day3_2
  (:require [clojure.string :as string]
            [clojure.test :refer :all]
            [clojure.pprint :refer :all]))

(defn parse [word] (zipmap [:id :left :top :width :height]
                           (map read-string (re-seq #"\d+" word))))
(def test-input "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2")

(defn prepare-input-data [input-string]
  (let [as-string-list (string/split-lines input-string)
        parsed-maps-list (map parse as-string-list)]
    (mapv #(into {} %) (into [] parsed-maps-list))))

(defn generate-2d-array [maps-list]
  (let
    [field-width (reduce max (map #(reduce + (vals (select-keys % [:left :width]))) maps-list))
     field-height (reduce max (map #(reduce + (vals (select-keys % [:top :height]))) maps-list))]
    (make-array Integer/TYPE field-height field-width)))

(defn pprint-2d-array [ar]
  (println (map #(with-out-str (pprint %)) ar)))

(defn update-field [field {:keys [left top width height]}]
  (let [x-range (range left (+ left width))
        y-range (range top (+ top height))]
    (doseq [x x-range y y-range]
      (let [
            row (aget field y)
            cell-value (aget row x)]
        (aset row x (inc cell-value))))
    field))

(defn is-not-shared-patch [field {:keys [id left top width height]}]
  (let [x-range (range left (+ left width))
        y-range (range top (+ top height))
        patch-values (for [x x-range y y-range :let [value (get-in field [y x])]]  value)]
    (every? #(= 1 %) patch-values)))

(defn get-result [input-list]
  (let [input-maps  (prepare-input-data input-list)
        field (generate-2d-array input-maps)]
    (reduce update-field field input-maps)
    (:id (first (filter #(is-not-shared-patch field %) input-maps)))))


(get-result test-input)

(deftest a-test
  (testing (is (= 3 (get-result test-input)))))
(a-test)


(let [input-data (slurp "input")]
  (println (str "Answer: " (time (get-result input-data)))))

