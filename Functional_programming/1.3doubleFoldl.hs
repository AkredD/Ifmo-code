fold :: Num a => [a] -> (a, a)
fold x = foldlDouble (+) (*) (0,1) x

foldlDouble :: (a -> a -> a) -> (a -> a -> a) -> (a, a) -> [a] -> (a, a)
foldlDouble _ _ (x,y) [] = (x,y)
foldlDouble f1 f2 (x, y) (z:zs) = foldlDouble f1 f2 (f1 x z, f2 y z) zs

fold' :: Num a => [a] -> (a,a) -> (a,a)
fold' a (rx, ry) = foldl (\(x,y) (rx, ry) -> (x+rx, y*ry)) (rx,ry) (zip a a)