foldlStrict :: (a -> b -> a) -> a -> [b] -> a
foldlStrict _ y [] = y
--foldlStrict f y (x:xs) = y' `seq` foldlStrict f y' xs where y' = f y x
foldlStrict f y (x:xs) = let y' = f y x in y' `seq` foldlStrict f y' xs

foldlDouble :: (a -> a -> a) -> a -> [a] -> a
foldlDouble _  x [] = x
foldlDouble f1 x (z:zs) = foldlDouble f1 (f1 x z) zs