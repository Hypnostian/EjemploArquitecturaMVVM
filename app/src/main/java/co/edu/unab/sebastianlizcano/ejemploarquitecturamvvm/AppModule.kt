package co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm

import android.content.Context
import androidx.room.Room
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskDao
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskDatabase
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(context, TaskDatabase::class.java, "task_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()

    @Provides
    @Singleton
    fun provideRepository(dao: TaskDao): TaskRepository = TaskRepository(dao)
}
