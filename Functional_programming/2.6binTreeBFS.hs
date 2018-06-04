data BinTree e = Empty | Node e (BinTree e) (BinTree e) deriving(Show, Eq, Ord)


instance Foldable BinTree where
    foldMap f a = foldBFS f [a]

foldBFS :: (Monoid m) => (a -> m) -> [BinTree a] -> m
foldBFS f [] = mempty
foldBFS f [Empty] = mempty
foldBFS f (Empty:ys) = foldBFS f ys
foldBFS f ((Node e l r):ys) = (f e) `mappend` (foldBFS f (ys ++ (l:r:[])))