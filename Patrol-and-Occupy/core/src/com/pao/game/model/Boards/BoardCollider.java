package com.pao.game.model.Boards;

import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;

public class BoardCollider {
    Board board;
    public BoardCollider(Board board){
        this.board = board;
    }
    public boolean checkBoardCollision(GameObject gameObject) {
        final int offset = 12;
        double X = gameObject.getX();
        double Y = gameObject.getY();
        return X >= board.getWidth() + offset || X <= -offset || Y >= board.getHeight() + offset || Y <= -offset;
    }

    public ModelPlayer checkBulletCollision(GameObject gameObject) {
        if (board.getBulletList() == null)
            return null;
        for (Bullet bullet : board.getBulletList())
            if (gameObject != bullet && gameObject.intersects(bullet) && !(gameObject instanceof Tank && ((Tank) gameObject).getColor() == bullet.getColor())) {
                return bullet.getColor();
            }
        return null;
    }
    public boolean checkTankCollision(GameObject gameObject) {
        if (board.getTankList() == null)
            return false;
        for (Tank tank : board.getTankList())
            if (gameObject != tank && gameObject.intersects(tank) && !(gameObject instanceof Bullet && ((Bullet) gameObject).getColor() == tank.getColor()))
                return true;
        return false;
    }

    public boolean checkObstacleCollision(GameObject gameObject) {
        if (board.getObstacleList() == null)
            return false;
        for (Obstacle obstacle : board.getObstacleList())
            if (gameObject != obstacle && gameObject.intersects(obstacle))
                return true;
        return false;
    }

    public boolean checkDynamiteCollision(GameObject gameObject) {
        if (board.getDynamiteList() == null)
            return false;
        for (Dynamite dynamite : board.getDynamiteList())
            if (gameObject != dynamite && gameObject.intersects(dynamite))
                return true;
        return false;
    }

    public boolean checkSpawnCollision(GameObject gameObject) {
        if(board.getSpawnList() == null)
            return false;
        for (Spawn spawn : board.getSpawnList())
            if (gameObject != spawn && gameObject.intersects(spawn)
                    && !(gameObject instanceof Tank && ((Tank)gameObject).getColor()==spawn.getColor())
                    && !(gameObject instanceof Bullet && ((Bullet)gameObject).getColor()==spawn.getColor()))
                return true;
        return false;
    }
    public boolean checkBreakableObstacleCollision(GameObject gameObject){
        if(board.getBreakableObstacleList() == null)
            return false;
        for (BreakableObstacle breakableObstacle : board.getBreakableObstacleList())
            if (gameObject != breakableObstacle && gameObject.intersects(breakableObstacle))
                return true;
        return false;
    }
}
