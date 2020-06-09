<?php

namespace Produits\ProduitsBundle\Repository;

/**
 * RatingRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class RatingRepository extends \Doctrine\ORM\EntityRepository
{

    public function getRating($idProd)
    {
        $query = $this->getEntityManager()->createQuery(
            'SELECT SUM(r.rate) FROM ProduitsBundle:Rating r where r.idProd = :idProd'
        )->setParameter('idProd',$idProd);

        return $query->getSingleScalarResult();
    }

    public function UpdateTotalRating($idProd,$idClient,$totalRate)
    {
        $query = $this->getEntityManager()->createQuery(

            'UPDATE ProduitsBundle:Rating r SET r.rate = :totalRate WHERE r.idProd= :idProd and r.idClient=:idClient'
        )->setParameter('idProd',$idProd)
            ->setParameter('totalRate',$totalRate)
            ->setParameter('idClient',$idClient);

        return $query->getResult();
    }

    public function countRating($idProd)
    {
        $query = $this->getEntityManager()->createQuery(
            'SELECT COUNT(r.rate) FROM ProduitsBundle:Rating r where r.idProd = :idProd'
        )->setParameter('idProd',$idProd);

        return $query->getSingleScalarResult();
    }

    public function checkRating($idProd,$idClient)
    {
        $query = $this->getEntityManager()->createQuery(
            'SELECT r.rate FROM ProduitsBundle:Rating r where r.idProd = :idProd and r.idClient=:idClient'
        )->setParameter('idProd',$idProd)
            ->setParameter('idClient',$idClient);

        return $query->getResult();
    }


    public function checkRating2($idProd)
    {
        $query = $this->getEntityManager()->createQuery(
            'SELECT r.rate FROM ProduitsBundle:Rating r where r.idProd = :idProd '
        )->setParameter('idProd',$idProd);

        return $query->getResult();
    }


    public function UpdateTotalRating2($idProd,$totalRate)
    {
        $query = $this->getEntityManager()->createQuery(

            'UPDATE ProduitsBundle:Rating r SET r.totalRate = :totalRate WHERE r.idProd= :idProd'
        )->setParameter('idProd',$idProd)
            ->setParameter('totalRate',$totalRate);

        return $query->getResult();
    }


    public function getTotalRate($idProd)
    {
        $query = $this->getEntityManager()->createQuery(
            'SELECT r.totalRate FROM ProduitsBundle:Rating r where r.idProd = :idProd '
        )->setParameter('idProd',$idProd);



        return $query->getResult();
    }

}